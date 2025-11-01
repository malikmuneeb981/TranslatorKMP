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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import languagesList
import org.example.project.domain.models.appModels.LangModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import translatorkmp.composeapp.generated.resources.Res
import translatorkmp.composeapp.generated.resources.ic_tick

@Composable
fun LanguageSelectionItem(langModel: LangModel,onLangClick:(LangModel)-> Unit){
    Row (modifier = Modifier.fillMaxWidth().shadow(
        elevation = 2.dp, shape = RoundedCornerShape(30.dp)
    ).clip(CircleShape).clickable(onClick = {
        onLangClick(langModel)
    }).background(brush = Brush.horizontalGradient(if (langModel.isSelected)
    listOf(Color(0xFF5BACFD),Color(0xFF103F6F))else listOf(Color.White, Color.White)),
        shape = CircleShape
    ).padding(
        horizontal = 15.dp, vertical = 10.dp
    ), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)){
        KamelImage(resource = { asyncPainterResource("https://flagpedia.net/data/flags/w702/${langModel.countryFlagCode}.webp?v=un") },
            contentDescription = null,
            modifier = Modifier.size(45.dp).clip(CircleShape),
            contentScale = ContentScale.Crop)

        Text(text = langModel.countryName, fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f),
            color = if (langModel.isSelected) Color.White else Color(0xFF353841))

        if (langModel.isSelected){
            Image(painter = painterResource(Res.drawable.ic_tick),
                contentDescription = null, colorFilter = ColorFilter.tint(color = Color.White
                ))
        }


    }
}

@Preview(showBackground = true)
@Composable
fun LanguageSelectionItemPrev(){
    LanguageSelectionItem(LangModel("Afrikaans", "af-ZA", "za")){

    }

}