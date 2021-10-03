package com.revbase.zaidanarrafif.presentation.student.quran_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant

@Composable
fun SurahNumber(
    surahNumber: Int,
    size: Dp = 40.dp,
    textSize: TextUnit = 20.sp,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        Image(
            painter = painterResource(id = R.drawable.octagon),
            contentDescription = "octagon bg"
        )
        Text(
            text = surahNumber.toString(),
            style = TextStyle(
                color = Color.White,
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontWeight = FontWeight.Bold,
                fontSize = textSize
            )
        )
    }
}