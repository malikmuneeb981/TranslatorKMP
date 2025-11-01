package org.example.project.presentation.screens

import From_Lang
import To_Lang
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mystic.ai.photo.editor.ui.Composables.HomeTopBar
import com.mystic.ai.photo.editor.ui.Composables.PoppinsText
import com.russhwolf.settings.Settings
import kotlinx.coroutines.launch
import languagesList
import org.example.project.commons.TranslationUtils
import org.example.project.commons.getPlatform
import org.example.project.presentation.composables.LangSelectionCard
import org.example.project.presentation.navigation.Navigation_Routes
import org.example.project.presentation.viewModels.TextTranslationViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import translatorkmp.composeapp.generated.resources.Res
import translatorkmp.composeapp.generated.resources.ic_copy
import translatorkmp.composeapp.generated.resources.ic_share
import translatorkmp.composeapp.generated.resources.ic_speaker

@Composable
fun TextTranslationScreen(
    navController: NavController,
    textTranslationViewModel: TextTranslationViewModel,
    translationUtils: TranslationUtils
){
    var textToTranslate by remember { mutableStateOf("") }
    val translationResult =
        //remember { mutableStateOf("") }
        textTranslationViewModel.translationResult.collectAsState()
    val settings = Settings()
    var fromLang by remember { mutableStateOf(settings.getInt(From_Lang,82)) }
    var toLang by remember { mutableStateOf(settings.getInt(To_Lang, 20)) }
    var startTts by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(key1 = startTts){
        if (startTts){
            translationUtils.startTTS(translationResult.value)
        }
        onDispose {
            translationUtils.stopTTS()
        }
    }
    Scaffold(topBar = {
        HomeTopBar(text = "Text Translation")
    }, snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }, containerColor = Color(0xFFF9F9F9), content = {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {


            LangSelectionCard(fromLang = fromLang,
                fromLangClick = {
                    navController.navigate(Navigation_Routes.LangSelectionFrom)
                }, toLang = toLang, switchLangClick = {

                    val temp = fromLang
                    fromLang = toLang
                    toLang = temp

                    settings.putInt(From_Lang, fromLang)
                    settings.putInt(To_Lang, toLang)


                }, toLangClick = {
                    navController.navigate(Navigation_Routes.LangSelectionTo)
                })

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 24.dp)
                .shadow(elevation = 2.dp,
                shape = RoundedCornerShape(28.dp)).background(
                Color.White).clip(shape = RoundedCornerShape(10.dp))){
                BasicTextField(
                    value = textToTranslate,
                    onValueChange = { textToTranslate = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    decorationBox = { innerTextField ->
                        Column {
                            PoppinsText(
                                text = languagesList[fromLang].countryName,
                                color = Color(0xFF404040),
                                fontsize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 24.dp, start = 8.dp)
                            )
                            Box(modifier = Modifier.weight(1f)
                                .padding(top = 10.dp, start = 8.dp))
                            {
                                if (textToTranslate.isEmpty()) {
                                    PoppinsText(
                                            text = "Enter your text",
                                            color = Color(0xFFAFAFAF),
                                            fontsize = 14.sp,
                                    )
                                }
                                innerTextField()
                            }
                        }

                    }
                )

                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 10.dp), horizontalArrangement = Arrangement.End) {

                    Box(modifier = Modifier.padding(
                        10.dp
                    ).clip(shape = RoundedCornerShape(30.dp)).background(
                        brush = Brush.horizontalGradient(listOf(Color(0xFF5BACFD),
                            Color(0xFF103F6F))), shape = RoundedCornerShape(30.dp)
                    ).padding(horizontal = 25.dp,
                        vertical = 9.dp).clickable(onClick = {
                       textTranslationViewModel.getTranslation(langFrom = languagesList[fromLang].countryCode, langTo = languagesList[toLang].countryCode, textToTranslate = textToTranslate)
                    })){

                        PoppinsText(text = "Translate", color = Color.White, fontsize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }

                }

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 24.dp)
                .shadow(elevation = 2.dp,
                    shape = RoundedCornerShape(28.dp)).background(
                    Color.White).clip(shape = RoundedCornerShape(10.dp))){
                PoppinsText(
                    text = languagesList[toLang].countryName,
                    color = Color(0xFF404040),
                    fontsize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 24.dp, start = 21.dp)
                )
                Text(
                    text = translationResult.value.ifEmpty {
                        "Translated text is here"
                    },
                    color = if (translationResult.value.isEmpty()) {
                        Color(0xFFAFAFAF)
                    } else {Color.Black},
                    fontSize = 16.sp,
                    minLines = 5,
                    modifier = Modifier.padding(top = 10.dp, start = 21.dp)

                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp).padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.End) {

                    Image(painter = painterResource(Res.drawable.ic_share),
                        contentDescription = null, modifier = Modifier.padding(end = 28.dp).clickable(onClick = {
                            translationUtils.shareText(text = translationResult.value)
                        }))
                    Image(painter = painterResource(Res.drawable.ic_copy),
                        contentDescription = null, modifier = Modifier.padding(end = 28.dp).clickable(onClick = {
                            translationUtils.copyToClipboard(translationResult.value)
                            if (getPlatform().name != "Android"){
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Text Copied ${
                                            if (translationResult.value.length>4)translationResult.value.slice(0..5) 
                                            else{
                                            translationResult.value }
                                        }..."

                                    )
                                }
                            }

                        }))
                    Image(painter = painterResource(Res.drawable.ic_speaker),
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = {
                            startTts = true
                        }))

                }

            }





        }
    })
}

@Preview
@Composable
fun TextTranslationScreenPrev(){
  // TextTranslationScreen()
}