package com.revbase.zaidanarrafif.presentation.login_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.DarkGrey
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun CustomPasswordTextField(
    modifier: Modifier = Modifier,
    text: String = "",
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
        var passwordVisibility by remember{ mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChanged(it)
                },
            value = text,
            onValueChange = { onTextChanged(it) },
            label = {
                Text(text = "Password")
            },
            isError = hasError,
            textStyle = TextStyle(
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = 21.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                cursorColor = Pink,
                focusedLabelColor = Pink,
                unfocusedBorderColor = if(text.isBlank()){
                    Color.Transparent
                } else {
                    DarkGrey
                },
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red
            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if(passwordVisibility){
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    Icon(icon, "visiblitiy_toogle")
                }
            }
        )
    }
}