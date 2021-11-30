package com.revbase.zaidanarrafif.presentation.student.tambah_hafalan.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue

@Composable
fun DeleteRecordButton(
    onClick: ()->Unit,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    cornerRadius: Dp = 8.dp,
    icon: ImageVector = Icons.Filled.Delete
) {
    Card(
        modifier = modifier.size(size),
        shape = RoundedCornerShape(cornerRadius),
        backgroundColor = Color.Red,
        elevation = 2.dp
    ) {
        IconButton(
            onClick = { onClick() },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "play surah",
                tint = Color.White
            )
        }
    }
}