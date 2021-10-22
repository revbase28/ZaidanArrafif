package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalMainItem

@ExperimentalCoilApi
@Composable
fun JurnalMainScreen(navController: NavController) {
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
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+"/ACTIVITY_JOURNAL")
                    }
                )
            }
            item {
                JournalMainItem(
                    journalType = "Jurnal Ibadah",
                    icon = R.drawable.jurnal_ibadah_icon,
                    progress = "5 dari 12 ibadah sudah rizki lakukan",
                    onClick = {
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+"/WORSHIP_JOURNAL")

                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}