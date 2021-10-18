package com.revbase.zaidanarrafif.presentation.common_component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AudioControl(
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit,
    playing: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp,
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.SkipPrevious,
                    contentDescription = "skip previous"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if(!playing){
                        onPause()
                    } else {
                        onPlay()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = if(!playing) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                    contentDescription = "play pause"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.SkipNext,
                    contentDescription = "skip next"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onStop() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Stop,
                    contentDescription = "stop"
                )
            }
        }
    }
}