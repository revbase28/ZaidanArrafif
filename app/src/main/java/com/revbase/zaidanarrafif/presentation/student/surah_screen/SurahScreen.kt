package com.revbase.zaidanarrafif.presentation.student.surah_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Constant.ALFATIHAH
import com.revbase.zaidanarrafif.common.Constant.ATTAUBAH
import com.revbase.zaidanarrafif.presentation.common_component.BlueButton
import com.revbase.zaidanarrafif.presentation.common_component.ErrorText
import com.revbase.zaidanarrafif.presentation.common_component.Loading
import com.revbase.zaidanarrafif.presentation.common_component.ScreenTitleBar
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.SurahInfo
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.SurahNumber
import com.revbase.zaidanarrafif.presentation.student.surah_screen.component.Bismillah
import com.revbase.zaidanarrafif.presentation.student.surah_screen.component.VerseItem

@Composable
fun SurahScreen(
    navController: NavController,
    surahNumber: Int,
    viewModel: SurahViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getSurahDetail(surahNumber)
    }
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
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
            ){
                SurahNumber(surahNumber = surahData.surahNumber, size = 48.dp, textSize = 24.sp)
                Spacer(modifier = Modifier.width(16.dp))
                SurahInfo(
                    revelation = surahData.revelation ,
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
            BlueButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                ,
                onClick = {  },
                text = "Ambil Quiz" ,
                textSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                if(surahNumber != ALFATIHAH && surahNumber != ATTAUBAH) {
                    item {
                        Bismillah()
                    }
                }
                items(surahData.verses) { verse ->
                    VerseItem(
                        verseData = verse,
                        onPlayAudioButtonClicked = {
                            viewModel.downloadAudioFromUrl(surahData)
                        }
                    )
                }
            }
        }
        if(state.error.isBlank()) {
            ErrorText(errorText = state.error)
        }
        if(state.isLoading) {
            Loading()
        }
    }
}