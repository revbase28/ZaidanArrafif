package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalItem
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalCoilApi
@Composable
fun DailyJournalScreen(
    savedState: Bundle,
    journalType: String,
    viewModel: JournalViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        if (journalType == "ACTIVITY_JOURNAL") {
            viewModel.getAllDailyActivityJournal()
        } else {

            viewModel.getAllDailyWorshipJournal()
        }
     viewModel.getTodaysJournal(JournalType.Today)
    }
    val state = viewModel.state.value
    val journalState = viewModel.journalState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        Text(
            text = "Assalamua'laikum ${savedState.getString("student_name")}",
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
        Text(
            text = "Daftar Kegiatan Kamu",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }  else if (state.error.isNotBlank()) {
            Log.d("gagal", state.error)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)

                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.studentActivityList) { activity ->
                    JournalItem(activityData = activity, todaysJournal = journalState.journal)
                }

            }

        }


    }
}