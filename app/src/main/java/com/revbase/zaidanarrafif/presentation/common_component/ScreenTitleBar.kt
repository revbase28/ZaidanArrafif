package com.revbase.zaidanarrafif.presentation.common_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant

@Composable
fun ScreenTitleBar(
    screenTitle: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack ,
                contentDescription = "back button"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = screenTitle,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Constant.LATO_FONT_FAMILY
            )
        )
    }
}