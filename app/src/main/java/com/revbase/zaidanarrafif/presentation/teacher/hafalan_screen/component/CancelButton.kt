package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun CancelButton(
    modifier: Modifier = Modifier,
    onClick: ()->Unit,
    text : String,
    textSize: TextUnit,
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Pink,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = textSize
            )
        )
    }
}