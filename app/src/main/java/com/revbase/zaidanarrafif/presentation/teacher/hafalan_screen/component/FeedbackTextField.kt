package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue

@Composable
fun FeedbackTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue,
            cursorColor = Blue
        ),
        shape = RoundedCornerShape(10.dp)
    )
}