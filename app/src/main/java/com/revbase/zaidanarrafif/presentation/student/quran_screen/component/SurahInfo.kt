package com.revbase.zaidanarrafif.presentation.student.quran_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.PlaceholderGrey


@Composable
fun SurahInfo(
    revelation: String,
    surahName: String,
    totalAyah: Int,
    surahNameTextSize: TextUnit = 16.sp,
    surahAdditionalInfoSize: TextUnit = 12.sp
) {
    Column {
        Text(
            text = "Surah $surahName",
            style = TextStyle(
                color = Color.Black,
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                fontSize = surahNameTextSize
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$revelation | ${totalAyah.toString()} Ayat",
            style = TextStyle(
                color = PlaceholderGrey,
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = surahAdditionalInfoSize
            )
        )
    }
}