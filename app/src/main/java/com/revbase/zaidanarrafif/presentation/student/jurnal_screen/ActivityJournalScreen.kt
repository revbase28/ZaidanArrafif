package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component.JournalItem

@Composable
fun ActivityJurnalScreen(
    navController:NavController,
) {
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

        LazyColumn (modifier = Modifier.fillMaxSize()){
            // Add a single item

            // Add 5 items
            items(10) { index ->
                JournalItem(jurnalData = Journal(
                    name = "Bangun Pukul 5 Pagi",
                    iconURL = "https://cdn-icons-png.flaticon.com/512/760/760644.png",
                    isDone = false
                ))
            }

            // Add another single item

        }


    }
}