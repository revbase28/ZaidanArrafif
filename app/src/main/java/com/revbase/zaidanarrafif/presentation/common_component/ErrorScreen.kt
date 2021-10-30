package com.revbase.zaidanarrafif.presentation.common_component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant

@Composable
fun ErrorScreen(
    message: String,
    modifier: Modifier = Modifier,
    showButton: Boolean = false,
    onButtonClicked: () -> Unit = {},
    buttonText: String = "",
    anim : Int = R.raw.no_internet
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(anim))

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition ,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Constant.LATO_FONT_FAMILY
            )
        )
        if(showButton) {
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onButtonClicked() },
                text = buttonText,
                textSize = 16.sp
            )
        }
    }
}