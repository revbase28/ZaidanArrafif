package com.revbase.zaidanarrafif.presentation.student.tambah_hafalan.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.PlayAudioButton
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.SurahInfo
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.SurahNumber

@ExperimentalMaterialApi
@Composable
fun RecordResultInfo(
    fileName: String,
    onPlayAudioClicked: () -> Unit,
    onDeleteRecord: ()->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DeleteRecordButton(
                onClick = {
                    onDeleteRecord()
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = fileName,
                modifier = Modifier
                    .weight(0.85F),
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            PlayAudioButton(
                onClick = {
                    onPlayAudioClicked()
                }
            )
        }
    }
}