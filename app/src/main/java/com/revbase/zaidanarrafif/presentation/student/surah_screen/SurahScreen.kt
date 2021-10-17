package com.revbase.zaidanarrafif.presentation.student.surah_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Constant.ALFATIHAH
import com.revbase.zaidanarrafif.common.Constant.ATTAUBAH
import com.revbase.zaidanarrafif.common.Constant.DOWNLOAD_DONE
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.models.SurahDetail
import com.revbase.zaidanarrafif.presentation.common_component.*
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.*
import com.revbase.zaidanarrafif.presentation.student.surah_screen.component.Bismillah
import com.revbase.zaidanarrafif.presentation.student.surah_screen.component.VerseItem

@Composable
fun SurahScreen(
    navController: NavController,
    surahNumber: Int,
    viewModel: SurahViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val downloadState = viewModel.downloadState
    var currentPlayedSurah by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("Terjadi kesalahan tidak terduga, coba lagi nanti") }
    var currentDownloadedAyah by remember { mutableStateOf(0) }
    var isConfirmDialogShown by remember { mutableStateOf(false) }
    var isDownloadingDialogShown by remember { mutableStateOf(false) }
    var isDownloadErrorDialogShown by remember { mutableStateOf(false) }
    var surahClickedData: SurahDetail? by remember {
        mutableStateOf(null)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getSurahDetail(surahNumber)
    }
    LaunchedEffect(key1 = downloadState.value) {
        downloadState.value.data?.let {
            if (it == DOWNLOAD_DONE)
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
            .padding(top = 16.dp)
    ) {
        if (isConfirmDialogShown) {
            ConfirmALertDialog(
                onDismiss = { isConfirmDialogShown = false },
                onConfirm = {
                    isConfirmDialogShown = false
                    surahClickedData?.let {
                        viewModel.downloadAudioFromUrl(it)
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

        ScreenTitleBar(
            screenTitle = "Baca Qur'an",
            navController = navController,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        state.data?.let { surahData ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SurahNumber(surahNumber = surahData.surahNumber, size = 48.dp, textSize = 24.sp)
                Spacer(modifier = Modifier.width(16.dp))
                SurahInfo(
                    revelation = surahData.revelation,
                    surahName = surahData.name,
                    totalAyah = surahData.numberOfVerses,
                    surahNameTextSize = 20.sp,
                    surahAdditionalInfoSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Sudah hafal Surat ini ?",
                style = TextStyle(
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { },
                text = "Ambil Quiz",
                textSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                if (surahNumber != ALFATIHAH && surahNumber != ATTAUBAH) {
                    item {
                        Bismillah()
                    }
                }
                items(surahData.verses) { verse ->
                    VerseItem(
                        verseData = verse,
                        onPlayAudioButtonClicked = {
                            if (!viewModel.checkIfFolderExist(surahData.name)) {
                                isConfirmDialogShown = true
                                surahClickedData = surahData
                            } else {
                                viewModel.playAudio(surahData.name, verse.verseNumber)
                            }
                        }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            ErrorScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                message = state.error,
                showButton = true,
                buttonText = "Coba lagi",
                onButtonClicked = {
                    viewModel.getSurahDetail(surahNumber)
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
    }
}