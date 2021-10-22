package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalItem
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.ListSurahItem

@ExperimentalCoilApi
@Composable
fun DailyJournalScreen(
    navController: NavController,
    journalType: String,
    viewModel: JurnalViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        if (journalType == "ACTIVITY_JOURNAL") {
            viewModel.getAllDailyActivityJournal()
        } else {

            viewModel.getAllDailyWorshipJournal()
        }
    }
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
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
        Text(
            text = "Daftar Kegiatan Kamu",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Constant.LATO_FONT_FAMILY
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = Color.Blue,
                )
            }
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
                items(state.journalList) { journal ->
                    JournalItem(jurnalData = journal)
                }

            }

        }


    }
}