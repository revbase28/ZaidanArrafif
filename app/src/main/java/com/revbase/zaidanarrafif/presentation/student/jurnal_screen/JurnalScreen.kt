package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun JurnalScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Jurnal")
    }
}