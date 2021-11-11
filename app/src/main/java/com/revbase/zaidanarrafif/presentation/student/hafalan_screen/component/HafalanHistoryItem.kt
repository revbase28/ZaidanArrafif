package com.revbase.zaidanarrafif.presentation.student.hafalan_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Tools
import com.revbase.zaidanarrafif.common.Tools.dateTimeStringToString
import com.revbase.zaidanarrafif.presentation.student.main_student.component.StartCount
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.PlaceholderGrey

@ExperimentalMaterialApi
@Composable
fun HafalanHistoryItem(
    surah: String,
    collectedDate: String,
    star : Int,
    onClick: ()->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        onClick = onClick
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
                    text = "Surah $surah",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Disetor tanggal ${dateTimeStringToString(collectedDate)}",
                    style = TextStyle(
                        color = PlaceholderGrey,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontSize = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Status : ")
                        }
                        append(if(star != 0) "Sudah dinilai" else "Belum dinilai")
                    },
                    fontSize = 12.sp,
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    color = if (star != 0) Color.Green else Color.Red
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            StartCount(count = star)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}