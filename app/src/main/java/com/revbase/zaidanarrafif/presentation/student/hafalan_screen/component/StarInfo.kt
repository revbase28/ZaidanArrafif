package com.revbase.zaidanarrafif.presentation.student.hafalan_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.Gold

@Composable
fun StarInfo(
    starCount: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(
            imageVector = Icons.Filled.Star,
            tint = Gold,
            contentDescription = "star"
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = starCount.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
    }
}