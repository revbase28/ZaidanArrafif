package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalMainItem

@ExperimentalCoilApi
@Composable
fun JurnalMainScreen(navController: NavController) {
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

        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                JournalMainItem(
                    journalType = "Jurnal Kegiatan",
                    imageUrl = "https://cdn-icons-png.flaticon.com/512/4852/4852363.png",
                    progress = "4 dari 11 kegiatan sudah rizki lakukan",
                    onClick = {
                        navController.navigate(com.revbase.zaidanarrafif.presentation.Screen.ActivityJournalScreen.route+"/ACTIVITY_JOURNAL")
                    }
                )
            }
            item {
                JournalMainItem(
                    journalType = "Jurnal Ibadah",
                    imageUrl = "https://cdn-icons-png.flaticon.com/512/3858/3858880.png",
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