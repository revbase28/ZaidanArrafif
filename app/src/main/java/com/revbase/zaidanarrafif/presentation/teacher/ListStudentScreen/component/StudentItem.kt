package com.revbase.zaidanarrafif.presentation.teacher.ListStudentScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.student.main_student.component.StartCount

@Composable
fun StudentItem(
    index: Int,
    studentName: String,
    studentStars: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${index + 1}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    modifier = Modifier.width(20.dp)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Image(
                    painter = rememberImagePainter(data = "https://www.seekpng.com/png/detail/349-3499598_portrait-placeholder-placeholder-person.png"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(percent = 50))
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = studentName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = Constant.LATO_FONT_FAMILY
                )
            }
            StartCount(count = studentStars)
        }
    }
}