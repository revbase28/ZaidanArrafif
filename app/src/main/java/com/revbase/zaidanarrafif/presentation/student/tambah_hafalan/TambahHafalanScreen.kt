package com.revbase.zaidanarrafif.presentation.student.tambah_hafalan

import android.Manifest
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Tools
import com.revbase.zaidanarrafif.common.Tools.getTimeStampAsString
import com.revbase.zaidanarrafif.common.Tools.removeWhiteSpaces
import com.revbase.zaidanarrafif.presentation.common_component.FailedActionAlertDialog
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.common_component.ScreenTitleBar
import com.revbase.zaidanarrafif.presentation.student.tambah_hafalan.components.RecordResultInfo
import com.revbase.zaidanarrafif.presentation.student.tambah_hafalan.components.TextFieldWithDropDown
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink
import kotlinx.coroutines.delay

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@Composable
fun TambahHafalanScreen(
    navController: NavController,
    viewModel: TambahHafalanViewModel = hiltViewModel()
) {

    var expanded by remember { mutableStateOf(false) }
    var surahName by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var recordingTime by remember { mutableStateOf(0) }
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )
    val postHafalanState = viewModel.postHafalanState.value
    var isRecording by remember { mutableStateOf(false) }
    var isStoped by remember { mutableStateOf(false) }
    var surahHasError by remember { mutableStateOf(false) }
    var recordingFile by remember { mutableStateOf("") }
    var showCantRecordDialog by remember{ mutableStateOf(false) }

    LaunchedEffect(key1 = isRecording, key2 = isStoped, key3 = recordingTime) {
        if (isRecording) {
            delay(1000L)
            recordingTime++
        }
        if (isStoped) {
            recordingTime = 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitleBar(
                screenTitle = "Tambah Hafalan",
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldWithDropDown(
                label = "Nama Surah",
                list = viewModel.surahList.value,
                text = surahName,
                onTextChanged = {
                    surahHasError = false
                    surahName = it
                    viewModel.filterSurah(it)
                    expanded = it.isNotBlank()
                },
                onIconCliked = {
                    expanded = !expanded
                },
                onDismiss = {
                    expanded = false
                },
                onDropdownMenuClicked = {
                    surahName = it
                    expanded = false
                },
                expanded = expanded,
                hasError = surahHasError
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = note,
                onValueChange = {
                    note = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = { Text("Catatan") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Pink,
                    cursorColor = Pink,
                    focusedLabelColor = Pink,
                    errorLabelColor = Color.Red,
                    errorBorderColor = Color.Red
                ),
                textStyle = TextStyle(
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (!isRecording) "Yuk rekam hafalanmu !" else "Hafalanmu sedang direkan",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Constant.LATO_FONT_FAMILY
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .size(100.dp),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Blue,
                elevation = 2.dp
            ) {
                IconButton(
                    modifier = Modifier.fillMaxSize(),
                    onClick = {
                        if (!permissionState.allPermissionsGranted) {
                            permissionState.launchMultiplePermissionRequest()
                        } else if (surahName.isNotBlank() && recordingFile.isBlank()) {
                            if (isRecording) {
                                recordingFile = viewModel.stopAudioRecording()
                                isStoped = true
                            } else {
                                viewModel.recordAudio("${surahName}_${viewModel.studentData.value.nama_siswa.removeWhiteSpaces()}_${getTimeStampAsString()}")
                                isStoped = false
                            }
                            isRecording = !isRecording
                        } else {
                            if (surahName.isBlank()) {
                                surahHasError = true
                            }
                            if (recordingFile.isBlank()) {
                                showCantRecordDialog = true
                            }
                        }
                    },
                ) {
                    Icon(
                        imageVector = if (!isRecording) Icons.Filled.Mic else Icons.Filled.Stop,
                        modifier = Modifier.size(56.dp),
                        contentDescription = "record hafalan",
                        tint = Color.White
                    )
                }
            }
            if (isRecording) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = Tools.intToHourMinuteFormat(recordingTime),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = Constant.LATO_FONT_FAMILY
                    )
                )
            }
            if (recordingFile.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Hasil Rekaman",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = Constant.LATO_FONT_FAMILY
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                RecordResultInfo(
                    fileName = recordingFile,
                    onPlayAudioClicked = {
                        viewModel.playAudio {

                        }
                    },
                    onDeleteRecord = {
                        viewModel.deleteRecording()
                        recordingFile = ""
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                PrimaryButton(
                    onClick = {
                        viewModel.postHafalan(surahName, note)
                    },
                    text = "Setor",
                    textSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        if(postHafalanState.isLoading) {
            LoadingScreen(
                modifier = Modifier.fillMaxSize(),
                isDimmed = true
            )
        }
        if(showCantRecordDialog){
            FailedActionAlertDialog(
                onDismiss = { showCantRecordDialog = false },
                title ="Kamu sudah merekam",
                message ="Hapus rekaman sebelumnya terlebih dulu jika mau ambil rekamaan lagi" ,
                animRes =R.raw.download_failed
            )
        }
    }
    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.stopAudio()
        }
    }
}