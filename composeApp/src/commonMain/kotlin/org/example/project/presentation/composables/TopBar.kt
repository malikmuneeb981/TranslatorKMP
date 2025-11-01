package com.mystic.ai.photo.editor.ui.Composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mystic.ai.photo.editor.ui.Utils.multipleEventsCutter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import translatorkmp.composeapp.generated.resources.Res
import translatorkmp.composeapp.generated.resources.ic_back

@Composable
fun TopBar(title:String,backclick:()->Unit) {

    Row (modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .height(60.dp)
        .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically){


        multipleEventsCutter { manager ->
            Image(painter = painterResource(Res.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier.padding(start = 15.dp).size(18.dp).clickable {
                    manager.processEvent {
                        backclick()
                    }
                })
            }


        PoppinsText(text = title, fontsize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold, modifier = Modifier
                .weight(1f)
                .padding(start = 15.dp),
            textAlign = TextAlign.Start,)






    }


}

@Preview
@Composable
private fun TopBarPrev() {

    TopBar("Text To Image"){

    }

}