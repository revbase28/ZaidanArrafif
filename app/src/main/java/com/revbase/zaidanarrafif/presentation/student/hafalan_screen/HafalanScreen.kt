package com.revbase.zaidanarrafif.presentation.student.hafalan_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HafalanScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hafalan")
    }
}