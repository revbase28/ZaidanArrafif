package com.revbase.zaidanarrafif.presentation.student.hafalan_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.student.main_student.component.StartCount
import com.revbase.zaidanarrafif.presentation.ui.theme.PlaceholderGrey

@Composable
fun HafalanHistoryItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column() {
                Text(
                    text = "Surah Al-Fatihah",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Disetor tanggal 10 Oktober 2020",
                    style = TextStyle(
                        color = PlaceholderGrey,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontSize = 12.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            StartCount(count = 5)
        }
    }
}