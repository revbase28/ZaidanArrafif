package com.revbase.zaidanarrafif.presentation.student.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.revbase.zaidanarrafif.presentation.Screen

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile")
    }
}