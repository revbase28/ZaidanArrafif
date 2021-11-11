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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.R
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Tools
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.presentation.common_component.*
import com.revbase.zaidanarrafif.presentation.login_screen.components.CustomTextField
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.CancelButton
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.FeedbackTextField
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.HafalanDetailCard
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import com.revbase.zaidanarrafif.presentation.ui.theme.LightGrey

@Composable
fun HafalanFeedbackScreen(
    navController: NavController,
    hafalanData: HafalanDTO,
    viewModel: HafalanFeedbackViewModel = hiltViewModel()
) {
    var comment by remember { mutableStateOf("") }
    var stars by remember { mutableStateOf("0") }
    var isSuccessDialogShown by remember { mutableStateOf(false) }

    val state = viewModel.state.value

    LaunchedEffect(key1 = state) {
        if (state.hafalan != null && !state.isLoading)
            isSuccessDialogShown = true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            ScreenTitleBar(
                screenTitle = "Feedback Hafalan",
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
                text = "Hafalan Tanggal ${Tools.dateTimeStringToString(hafalanData.date)}",
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )

            Text(
                text = hafalanData.siswa.nama_siswa,
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )

            Text(
                text = "Kelas ${hafalanData.siswa.kelas}",
                style = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            HafalanDetailCard(
                surah = hafalanData.surat,
                note = hafalanData.catatan,
                onPlayAudioClick = {
                    viewModel.playAudioFromUrl(hafalanData.link_rekaman)
                },
                onSubmitButtonClick = {
                    if (stars.isEmpty())
                        stars = "0"
                    viewModel.updateHafalan(
                        hafalanData.id_hafalan,
                        comment,
                        stars.toInt() ?: 0
                    )
                },
                onCommentTexFieldChange = {
                    comment = it
                },
                onStarTexFieldChange = {
                    stars = it
                },
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }
        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier.fillMaxSize(),
                isDimmed = true
            )
        }

        if (isSuccessDialogShown) {
            SuccessAlertDialog(
                onDismiss = { isSuccessDialogShown = false },
                title = "Berhasil",
                message = "Feedback hafalan berhasil dikirim",
                animRes = R.raw.success_anim
            )
        }
    }
}