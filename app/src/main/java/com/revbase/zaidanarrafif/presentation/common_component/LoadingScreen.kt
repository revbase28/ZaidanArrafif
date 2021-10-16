package com.revbase.zaidanarrafif.presentation.common_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition ,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
    }
}