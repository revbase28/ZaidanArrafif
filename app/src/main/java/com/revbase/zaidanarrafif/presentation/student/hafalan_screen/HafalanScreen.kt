package com.revbase.zaidanarrafif.presentation.student.hafalan_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.component.HafalanHistoryItem


@ExperimentalMaterialApi
@Composable
fun HafalanScreen(
    mainNavController: NavController,
    viewModel: HafalanViewModel = hiltViewModel(),
    studentData: Siswa
) {
    val state = viewModel.getHafalanState.value

    LaunchedEffect(key1 = Unit) {
        viewModel.getHafalanList()
    }

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
            text = "Yuk! Setor Hafalan ${studentData.nama_panggilan}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            onClick = {
                mainNavController.navigate(Screen.TambahHafalanScreen.route)
            },
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
        if(state.isLoading) {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            if(state.listHafalan.isNotEmpty()){
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.listHafalan) {
                        HafalanHistoryItem(
                            surah = it.surat,
                            collectedDate = it.date,
                            star = it.star,
                            onClick = {
                                mainNavController.currentBackStackEntry?.arguments?.putParcelable("hafalanData", it)
                                mainNavController.navigate(
                                    Screen.HafalanDetailScreen.route
                                )
                            }
                        )
                    }
                }
            } else {
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
    }
}