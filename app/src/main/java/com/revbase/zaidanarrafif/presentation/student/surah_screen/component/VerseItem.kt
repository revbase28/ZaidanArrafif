package com.revbase.zaidanarrafif.presentation.student.surah_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.revbase.zaidanarrafif.common.Constant.LATO_FONT_FAMILY
import com.revbase.zaidanarrafif.domain.models.VerseSimplified
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.PlayAudioButton
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.SurahNumber
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue

@Composable
fun VerseItem(
    verseData: VerseSimplified,
    onPlayAudioButtonClicked: ()->Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (verseData.verseNumber % 2 == 0) {
                    Blue.copy(alpha = 0.15f)
                } else Color.White
            )
            .padding(16.dp)
    ) {
        Column {
            SurahNumber(
                surahNumber = verseData.verseNumber,
                size = 32.dp,
                textSize = 12.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            PlayAudioButton(
                onClick = {
                    onPlayAudioButtonClicked()
                },
                size = 32.dp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = verseData.text,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = verseData.translation,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = LATO_FONT_FAMILY
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}