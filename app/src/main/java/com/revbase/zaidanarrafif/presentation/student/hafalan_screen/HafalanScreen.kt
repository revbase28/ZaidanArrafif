package com.revbase.zaidanarrafif.presentation.student.hafalan_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.component.HafalanHistoryItem


@Composable
fun HafalanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ){
        Text(
            text = "Lupa itu biasa, yang penting terus berusaha",
            fontSize = 14.sp,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Yuk! Setor Hafalan Rizki",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            onClick = {  },
            text = "Tambah Setoran Hafalan" ,
            textSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Riwayat Hafalan",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_box))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition ,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sepertinya kamu belum pernah menyetor hafalan, mulai setor hafalan yuk!",
                fontSize = 16.sp,
                fontFamily = Constant.LATO_FONT_FAMILY,
                textAlign = TextAlign.Center
            )
        }
    }
}