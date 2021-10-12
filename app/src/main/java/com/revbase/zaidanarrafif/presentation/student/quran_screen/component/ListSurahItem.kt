package com.revbase.zaidanarrafif.presentation.student.quran_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.revbase.zaidanarrafif.domain.models.Surah

@ExperimentalMaterialApi
@Composable
fun ListSurahItem(
    surahData: Surah,
    onClick: () -> Unit,
    onPlayAudioClicked: ()->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.fillMaxHeight()) {
                SurahNumber(surahNumber = surahData.surahNumber)
                Spacer(modifier = Modifier.width(16.dp))
                SurahInfo(revelation = surahData.revelation, surahName = surahData.name, totalAyah = surahData.numberOfVerses)
            }
            Spacer(modifier = Modifier.width(16.dp))
            PlayAudioButton(
                onClick = {
                    onPlayAudioClicked()
                }
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}