package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.common.Constant

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun JournalSummaryItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        shape = RoundedCornerShape(size = 10.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(
                    text = "Activity",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "xx Siswa Telah Melakukan Kegiatan",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Constant.LATO_FONT_FAMILY,
                        fontSize = 14.sp
                    ),
                )
            }
            
            Image(
                painter = rememberImagePainter("https://cdn-icons-png.flaticon.com/512/760/760644.png"),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}