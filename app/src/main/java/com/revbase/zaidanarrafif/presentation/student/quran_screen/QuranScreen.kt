package com.revbase.zaidanarrafif.presentation.student.quran_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.ErrorScreen
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.ConfirmALertDialog
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.DownloadAlertDialog
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.FailedToDownloadAlertDialog
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.ListSurahItem

@ExperimentalMaterialApi

@Composable
fun QuranScreen(
    navController: NavController,
    viewModel: QuranViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val downloadState = viewModel.downloadState
    var currentPlayedSurah by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("Terjadi kesalahan tidak terduga, coba lagi nanti") }
    var currentDownloadedAyah by remember { mutableStateOf(0) }
    var isConfirmDialogShown by remember { mutableStateOf(false) }
    var isDownloadingDialogShown by remember { mutableStateOf(false) }
    var isDownloadErrorDialogShown by remember { mutableStateOf(false) }

    var surahClickedData: Surah? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(key1 = downloadState.value) {
        downloadState.value.data?.let {
            if (it == Constant.DOWNLOAD_DONE)
                isDownloadingDialogShown = false
            else {
                isDownloadingDialogShown = true
                currentDownloadedAyah = it
            }
        } ?: print("data null\n")
        if (downloadState.value.error.isNotBlank()) {
            isDownloadingDialogShown = false
            isDownloadErrorDialogShown = true
            errorMessage = downloadState.value.error
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        if (isConfirmDialogShown) {
            ConfirmALertDialog(
                onDismiss = { isConfirmDialogShown = false },
                onConfirm = {
                    isConfirmDialogShown = false
                    surahClickedData?.let {
                        viewModel.getSurahDetailThenDownloadAudio(it.surahNumber)
                        currentPlayedSurah = it.name
                    }
                }
            )
        }
        if (isDownloadingDialogShown) {
            DownloadAlertDialog(
                downloadingFileName = "Surah $currentPlayedSurah ayat $currentDownloadedAyah"
            )
        }
        if (isDownloadErrorDialogShown) {
            FailedToDownloadAlertDialog(
                onDismiss = {
                    isDownloadErrorDialogShown = false
                    errorMessage = ""
                },
                message = errorMessage
            )
        }

        Text(
            text = "Assalamua'laikum Rizki",
            fontSize = 14.sp,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Selamat Belajar, Selamat Beraktivitas",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(onSearchButtonClicked = { })
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Daftar Surat",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (state.error.isNotBlank()) {
            ErrorScreen(
                modifier = Modifier.fillMaxSize(),
                message = state.error,
                showButton = true,
                buttonText = "Coba lagi",
                onButtonClicked = {
                    viewModel.getAllSurah()
                }
            )
        }
        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }
        if (state.listSurah.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.listSurah) { surah ->
                    ListSurahItem(
                        surahData = surah,
                        onClick = {
                            navController.navigate("${Screen.SurahScreen.route}/${surah.surahNumber}")
                        },
                        onPlayAudioClicked = {
                            if (!viewModel.checkIfFolderExist(surah.name)) {
                                isConfirmDialogShown = true
                                surahClickedData = surah
                            } else {
                                Log.d("play audio", "Already downloaded, should play surah instead")
                            }
                        }
                    )
                }
            }
        }
    }
}