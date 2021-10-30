package com.revbase.zaidanarrafif.presentation.login_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.DarkGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    label: String,
    color: Color,
    height: Dp = 62.dp,
    onTextChanged: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    hasError: Boolean
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(4.dp)
                )
                .fillMaxWidth()
                .height(height - 8.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChanged(it)
                },
            value = text,
            isError = hasError,
            onValueChange = { onTextChanged(it) },
            label = {
                Text(text = label)
            },
            textStyle = TextStyle(
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = 21.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if(hasError) Color.Red else Pink,
                cursorColor = Pink,
                focusedLabelColor = if(hasError) Color.Red else Pink,
                unfocusedBorderColor = if(text.isBlank()){
                    Color.Transparent
                } else {
                    DarkGrey
                },
                errorLabelColor = Color.Red,
                errorBorderColor = Color.Red
            )
        )
    }
}