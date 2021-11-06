package com.revbase.zaidanarrafif.presentation.common_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant

@Composable
fun ErrorText(
    errorText: String,
    size: TextUnit = 12.sp,
    modifier: Modifier = Modifier
) {
    Text(
        text = errorText,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = modifier,
        fontFamily = Constant.LATO_FONT_FAMILY,
        fontSize = size
    )
}