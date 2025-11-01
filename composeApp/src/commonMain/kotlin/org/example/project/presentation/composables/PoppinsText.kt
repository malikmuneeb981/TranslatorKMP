package com.mystic.ai.photo.editor.ui.Composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun PoppinsText(modifier: Modifier = Modifier,
                text:String,
                fontStyle: FontStyle =FontStyle.Normal,
                textAlign: TextAlign = TextAlign.Center,
                fontWeight: FontWeight = FontWeight.Normal,
                fontsize:TextUnit = 15.sp,
                color:Color = Black

                ) {

    Text(modifier = modifier,
        text = text,
        fontStyle = fontStyle,
        textAlign = textAlign,
        fontSize = fontsize,
        fontWeight = fontWeight,
        color = color,


        )

}