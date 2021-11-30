package com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.revbase.zaidanarrafif.presentation.student.quran_screen.component.PlayAudioButton
import com.revbase.zaidanarrafif.presentation.ui.theme.Pink

@Composable
fun RecordingPlayer(
    modifier: Modifier = Modifier,
    onPlayButtonClicked: ()->Unit,
    progress: Float
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayAudioButton(
            onClick = { onPlayButtonClicked() }
        )
        Spacer(modifier = modifier.width(8.dp))
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                ,
                progress = progress,
                color = Pink,
                backgroundColor = Pink.copy(0.25F)
            )
        }
    }
}