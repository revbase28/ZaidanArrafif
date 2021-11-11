package com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.component.StarInfo
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.PlaceholderGrey

@Composable
fun HafalanInfo(
    modifier: Modifier = Modifier,
    surahName: String,
    collectedDate: String,
    star: Int,
    isRated: Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Surah $surahName",
                fontWeight = Bold,
                fontSize = 20.sp,
                fontFamily = Constant.LATO_FONT_FAMILY,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Disetor tanggal $collectedDate",
                fontSize = 14.sp,
                fontFamily = Constant.LATO_FONT_FAMILY,
                color = PlaceholderGrey
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = Bold,
                            color = Color.Black
                        )
                    ) {
                        append("Status : ")
                    }
                    append(if(isRated) "Sudah dinilai" else "Belum dinilai")
                },
                fontSize = 14.sp,
                fontFamily = Constant.LATO_FONT_FAMILY,
                color = if (isRated) Color.Green else Color.Red
            )
        }
        StarInfo(starCount = star)
    }
}