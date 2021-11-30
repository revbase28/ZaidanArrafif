package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton

@Composable
fun TableCell(
    index: Int,
    studentName: String,
    surahName: String,
    onButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${index + 1}",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.1f),
            textAlign = TextAlign.Center
        )

        Text(
            text = studentName,
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(0.3f)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = surahName,
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center
        )

        PrimaryButton(
            onClick = {
                onButtonClick()
            },
            text = "Detail",
            textSize = 16.sp,
            modifier = Modifier
                .weight(0.3f)
                .padding(8.dp)
        )
    }
}