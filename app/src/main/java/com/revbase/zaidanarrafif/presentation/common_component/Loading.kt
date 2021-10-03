package com.revbase.zaidanarrafif.presentation.common_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Pink)
    }
}