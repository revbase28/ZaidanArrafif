package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.common_component.PrimaryButton
import com.revbase.zaidanarrafif.presentation.common_component.ScreenTitleBar
import com.revbase.zaidanarrafif.presentation.common_component.SecondaryButton
import com.revbase.zaidanarrafif.presentation.login_screen.components.CustomTextField
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.CancelButton
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.FeedbackTextField
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey

@Composable
fun HafalanFeedbackScreen(
    navController: NavController
) {
    var comment by remember { mutableStateOf("") }
    var stars by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        ScreenTitleBar(
            screenTitle = "Detail Kegiatan",
            navController = navController,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        val textStyle = TextStyle(
            color = Color.Black,
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Hafalan Tanggal d-m-Y",
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        )

        Text(
            text = "Rizki Maulana",
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        )

        Text(
            text = "Kelas 1Z",
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

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
                    text = "Surat: Ad-dhuha",
                    style = textStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp)
                )

                Text(
                    text = "Rekaman Hafalan",
                    style = textStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    IconButton(
                        onClick = {  },
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
                    },
                    text = stars,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    CancelButton(
                        onClick = { /*TODO*/ },
                        text = "Batal",
                        textSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(0.48f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    PrimaryButton(
                        onClick = { /*TODO*/ },
                        text = "Submit",
                        textSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}