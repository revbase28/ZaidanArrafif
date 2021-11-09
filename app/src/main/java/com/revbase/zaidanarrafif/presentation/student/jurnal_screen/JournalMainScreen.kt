package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
<<<<<<< Updated upstream
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
=======
import androidx.compose.runtime.*
>>>>>>> Stashed changes
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
<<<<<<< Updated upstream
import androidx.compose.ui.unit.sp
=======
import androidx.hilt.navigation.compose.hiltViewModel
>>>>>>> Stashed changes
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalMainItem
<<<<<<< Updated upstream

@ExperimentalCoilApi
@Composable
fun JurnalMainScreen(navController: NavController) {
=======


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

>>>>>>> Stashed changes
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
                    progress = "4 dari 11 kegiatan sudah rizki lakukan",
                    onClick = {
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+"/" + JournalType.Kegiatan.queryString)
                    }
                )
            }
            item {
                JournalMainItem(
                    journalType = "Jurnal Ibadah",
                    icon = R.drawable.jurnal_ibadah_icon,
                    progress = "5 dari 12 ibadah sudah rizki lakukan",
                    onClick = {
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+ "/" + JournalType.Ibadah.queryString)

                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}