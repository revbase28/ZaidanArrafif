package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue

@Composable
fun HafalanDetailCard(
    surah: String,
    note: String?,
    onPlayAudioClick: () -> Unit,
    onSubmitButtonClick: () -> Unit,
    onCommentTexFieldChange: (String) -> Unit,
    onStarTexFieldChange: (String) -> Unit,
    onBackButtonClick: () -> Unit
) {
    var comment by remember { mutableStateOf("") }
    var stars by remember { mutableStateOf("") }

    val textStyle = TextStyle(
        color = Color.Black,
        fontFamily = Constant.LATO_FONT_FAMILY,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 14.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 14.dp)
        ) {
            Text(
                text = "Surat: $surah",
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )

            Text(
                text = "Catatan: ${note ?: "Tidak ada catatan"}",
                style = textStyle,
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 14.dp)
            )

            Text(
                text = "Rekaman Hafalan",
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                IconButton(
                    onClick = { onPlayAudioClick() },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Blue)
                        .size(72.dp)
                        .align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Filled.VolumeUp,
                        contentDescription = "play surah",
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Text(
                text = "Komentar",
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            )

            FeedbackTextField(
                text = comment,
                onValueChange = {
                    comment = it
                    onCommentTexFieldChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            )

            Text(
                text = "Jumlah star yang diberikan",
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            )

            FeedbackTextField(
                onValueChange = {
                    stars = it
                    onStarTexFieldChange(it)
                },
                text = stars,
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                CancelButton(
                    onClick = { onBackButtonClick() },
                    text = "Batal",
                    textSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(0.48f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                PrimaryButton(
                    onClick = { onSubmitButtonClick() },
                    text = "Submit",
                    textSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}