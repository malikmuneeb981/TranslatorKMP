package org.example.project.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mystic.ai.photo.editor.ui.Composables.PoppinsText
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import languagesList
import org.example.project.presentation.navigation.Navigation_Routes
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import translatorkmp.composeapp.generated.resources.Res
import translatorkmp.composeapp.generated.resources.ic_drop_down_arrow
import translatorkmp.composeapp.generated.resources.ic_interchange_lang

@Composable
fun LangSelectionCard(fromLang:Int,fromLangClick:()-> Unit,toLang: Int,
                      toLangClick:()-> Unit,switchLangClick:()-> Unit){
    Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp).padding(top = 10.dp).shadow(elevation = 10.dp,
        shape = RoundedCornerShape(15.dp)).background(
        color = Color.White , shape = RoundedCornerShape(15.dp)
    ).padding(horizontal = 16.dp,
        vertical = 7.dp), horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,){
        Row(modifier = Modifier.weight(1f).clickable(onClick = {
            fromLangClick()

        }), verticalAlignment = Alignment.CenterVertically) {

            KamelImage(resource = { asyncPainterResource("https://flagpedia.net/data/flags/w702/${languagesList[fromLang].countryFlagCode}.webp?v=un") }, contentDescription = null,
                modifier = Modifier.size(35.dp).clip(CircleShape),
                contentScale = ContentScale.Crop)
            PoppinsText(text = languagesList[fromLang].countryName, fontsize = 14.sp,
                color = Color(0xFF060606), modifier = Modifier.padding(start = 14.dp))
            Image(painter = painterResource(Res.drawable.ic_drop_down_arrow),
                contentDescription = null, modifier = Modifier.padding(start = 12.dp))
        }
        Image(painter = painterResource(Res.drawable.ic_interchange_lang),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = {
                switchLangClick()
            }))

        Row(modifier = Modifier.weight(1f).clickable(onClick = {
            toLangClick()

        }), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {

            KamelImage(resource = { asyncPainterResource("https://flagpedia.net/data/flags/w702/${languagesList[toLang].countryFlagCode}.webp?v=un") }, contentDescription = null,
                modifier = Modifier.size(35.dp).clip(CircleShape),
                contentScale = ContentScale.Crop)
            PoppinsText(text = languagesList[toLang].countryName, fontsize = 14.sp,
                color = Color(0xFF060606), modifier = Modifier.padding(start = 14.dp))
            Image(painter = painterResource(Res.drawable.ic_drop_down_arrow),
                contentDescription = null, modifier = Modifier.padding(start = 12.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LangSelectionCardPrev(){
    LangSelectionCard(fromLang = 20, toLang = 82, fromLangClick = {

    }, switchLangClick = {

    }, toLangClick = {

    })
}
