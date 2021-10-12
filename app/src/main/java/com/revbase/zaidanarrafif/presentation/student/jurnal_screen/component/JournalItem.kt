package com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.Journal
import java.lang.reflect.Modifier

@ExperimentalCoilApi
@Composable
fun JournalItem(
    jurnalData: Journal,
    textSize: TextUnit = 14.sp,
) {
    Card(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
    ) {
        Row(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text =jurnalData.name ,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    fontWeight = FontWeight.Bold,
                    fontSize = textSize
                )
            )
            Image(
                painter = rememberImagePainter(jurnalData.iconURL),
                contentDescription = null,
                modifier = androidx.compose.ui.Modifier
                    .size(50.dp)
            )
            JournalCheckBox()
        }
    }
    Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
}