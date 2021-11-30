package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant

@Composable
fun TableHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.1f),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Nama",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Surat",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Komentar & Rekaman",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center
        )
    }
}