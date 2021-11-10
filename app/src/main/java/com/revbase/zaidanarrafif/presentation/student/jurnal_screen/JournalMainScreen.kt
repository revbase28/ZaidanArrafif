package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalMainItem
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow


@ExperimentalCoilApi
@Composable
fun JurnalMainScreen(navController: NavController, viewModel:JournalViewModel = hiltViewModel()) {
    var totalActivity by remember {
        mutableStateOf(0)
    }
    var totalWorship by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = Unit ){

        viewModel.getTodaysJournal(JournalType.Ibadah)
        viewModel.getTodaysJournal(JournalType.Kegiatan)
    }
    val activity = viewModel.kegiatanJournalState.value.journal
    if(activity==null){
        totalActivity = 0
    }
    else{
        totalActivity = activity.activityPerformed.size
    }

    val worship = viewModel.ibadahJournalState.value.journal
    if(worship==null){
        totalWorship = 0
    }
    else{
        totalWorship = worship.activityPerformed.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                JournalMainItem(
                    journalType = "Jurnal Kegiatan",
                    icon = R.drawable.jurnal_kegiatan_icon,
                    progress = " $totalActivity dari 11 kegiatan sudah rizki lakukan",
                    onClick = {
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+"/ACTIVITY_JOURNAL")
                    }
                )
            }
            item {
                JournalMainItem(
                    journalType = "Jurnal Ibadah",
                    icon = R.drawable.jurnal_ibadah_icon,
                    progress = "$totalWorship dari 14 ibadah sudah rizki lakukan",
                    onClick = {
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+"/WORSHIP_JOURNAL")

                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}