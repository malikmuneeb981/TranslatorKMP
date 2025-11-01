package org.example.project.presentation.screens

import From_Lang
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mystic.ai.photo.editor.ui.Composables.PoppinsText
import com.mystic.ai.photo.editor.ui.Composables.TopBar
import com.russhwolf.settings.Settings
import languagesList
import org.example.project.domain.models.appModels.LangModel
import org.example.project.presentation.composables.LanguageSelectionItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import translatorkmp.composeapp.generated.resources.Res
import translatorkmp.composeapp.generated.resources.ic_langSeach

@Composable
fun FromLangSelectionScreen(navController: NavController) {

    val allLanguages = remember { languagesList } // original source
    val filteredList = remember { mutableStateListOf<LangModel>().apply { addAll(allLanguages) } }

    var langSearch by remember { mutableStateOf("") }
    val settings = Settings()

    Scaffold(
        topBar = {
            TopBar(title = "Select Language", backclick = {
                navController.popBackStack()
            })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 15.dp)
            ) {

                // 🔍 Search Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .shadow(3.dp, RoundedCornerShape(30.dp))
                        .background(Color.White, RoundedCornerShape(30.dp))
                        .padding(horizontal = 24.dp, vertical = 18.dp)
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    BasicTextField(
                        value = langSearch,
                        onValueChange = { query ->
                            langSearch = query
                            filteredList.clear()
                            if (query.isBlank()) {
                                filteredList.addAll(allLanguages)
                            } else {
                                filteredList.addAll(
                                    allLanguages.filter {
                                        it.countryName.contains(query, ignoreCase = true)
                                    }
                                )
                            }
                        },
                        decorationBox = { innerTextField ->
                            Row {
                                Image(
                                    painter = painterResource(Res.drawable.ic_langSeach),
                                    contentDescription = null
                                )
                                Box(modifier = Modifier.weight(1f)
                                        .padding(start = 16.dp)) {
                                    if (langSearch.isEmpty()) {
                                        PoppinsText(
                                            text = "Search Language",
                                            fontsize = 18.sp,
                                            color = Color(0xFF6A6A6A),

                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            color = Color(0xFF6A6A6A),
                            fontSize = 18.sp
                        )
                    )
                }

                // 🗂 Language List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 6.dp),
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    items(filteredList) { lang ->
                        LanguageSelectionItem(langModel = lang, onLangClick = {
                            allLanguages.forEachIndexed { index, item ->
                                allLanguages[index] = item.copy(isSelected = item == lang)
                            }
                            filteredList.clear()
                            filteredList.addAll(
                                if (langSearch.isBlank()) allLanguages
                                else allLanguages.filter {
                                    it.countryName.contains(langSearch, ignoreCase = true)
                                }
                            )
                            val selectedLang = languagesList.find { langs->
                                langs.countryCode == lang.countryCode
                            }
                            settings.putInt(From_Lang,languagesList.indexOf(selectedLang))
                            navController.popBackStack()

                        })
                    }
                }
            }
        },
        containerColor = Color.White
    )
}

@Preview
@Composable
fun FromLangSelectionScreenPrev() {
    FromLangSelectionScreen(rememberNavController())
}
