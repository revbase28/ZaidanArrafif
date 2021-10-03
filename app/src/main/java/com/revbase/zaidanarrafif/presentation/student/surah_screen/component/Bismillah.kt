package com.revbase.zaidanarrafif.presentation.student.surah_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue

@Composable
fun Bismillah() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Blue.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bismillah),
            contentDescription = "bismillah",
            modifier = Modifier.padding(4.dp)
        )
    }
}