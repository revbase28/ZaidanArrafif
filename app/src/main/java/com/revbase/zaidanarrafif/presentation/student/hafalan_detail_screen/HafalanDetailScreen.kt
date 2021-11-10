package com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Tools
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.presentation.common_component.ScreenTitleBar
import com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen.components.HafalanInfo
import com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen.components.RecordingPlayer
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.PlayAudioButton
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun HafalanDetailScreen(
    navController: NavController,
    hafalanData: HafalanDTO,
    viewModel: HafalanDetailViewModel = hiltViewModel()
) {

    var audioProgress by remember{ mutableStateOf(viewModel.audioProgress) }
    var audioDuration by remember{ mutableStateOf(viewModel.duration) }
    var currentAudioSec by remember{ mutableStateOf(viewModel.currentSec) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenTitleBar(
            screenTitle = "Detail Hafalan",
            navController = navController
        )
        Spacer(modifier = Modifier.height(16.dp))
        HafalanInfo(
            surahName = hafalanData.surat,
            collectedDate = Tools.dateTimeStringToString(hafalanData.date),
            star = hafalanData.star,
            isRated = hafalanData.star != 0,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Rekaman",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecordingPlayer(
            onPlayButtonClicked = { viewModel.playAudioFromUrl(hafalanData.link_rekaman) },
            progress = audioProgress.value
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Catatan",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = hafalanData.catatan ?: "Tidak ada catatan",
            fontSize = 14.sp,
            fontFamily = Constant.LATO_FONT_FAMILY,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Komentar",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = hafalanData.komentar ?: "Tidak / belum ada komentar",
            fontSize = 14.sp,
            fontFamily = Constant.LATO_FONT_FAMILY,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = audioProgress.value.toString())
        Text(text = audioDuration.value.toString())
        Text(text = currentAudioSec.value.toString())
    }
}