package com.mystic.ai.photo.editor.ui.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import translatorkmp.composeapp.generated.resources.Res
import translatorkmp.composeapp.generated.resources.ic_hamburger
import translatorkmp.composeapp.generated.resources.ic_topbarpremium

@Composable
fun HomeTopBar(text:String) {

    Row(modifier = Modifier.fillMaxWidth().statusBarsPadding().height(60.dp)
        .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {


        PoppinsText(text = text,
            fontWeight = FontWeight.SemiBold,
            fontsize = 20.sp,
            modifier = Modifier.weight(1f))




    }

}


@Preview
@Composable
private fun HomeTopBarPrev() {

    HomeTopBar("Text To Image")

}