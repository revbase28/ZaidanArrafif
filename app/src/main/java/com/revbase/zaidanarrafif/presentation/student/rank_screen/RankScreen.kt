package com.revbase.zaidanarrafif.presentation.student.rank_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.domain.models.LeaderboardStudentDetail
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.student.rank_screen.component.RankItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed

@ExperimentalCoilApi
@Composable
fun RankScreen(
    viewModel: RankViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        LaunchedEffect(key1 = Unit) {
            viewModel.getLeaderboard()
        }
        val state = viewModel.state.value

        Text(
            text = "Assalamu'alaikum ${viewModel.student?.nama_panggilan}",
            style = TextStyle(
                color = Color.Black,
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
        } else if (state.error.isNotBlank()) {
            Log.d("gagal", state.error)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)

                )
            }
        } else {
            Text(
                text = "Peringkat Kamu Saat Ini",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            state.leaderboard?.let {
                RankItem(
                    position = it.studentRank,
                    leaderboardStudentDetail = LeaderboardStudentDetail(
                        name = if (viewModel.student != null) viewModel.student!!.nama_siswa else "",
                        stars = it.stars
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Peringkat Kelas Saat Ini",
                style = TextStyle(
                    fontFamily = Constant.LATO_FONT_FAMILY,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                state.leaderboard?.let {
                    itemsIndexed(items = it.leaderboard) {index, item ->
                        RankItem(
                            position = index + 1,
                            leaderboardStudentDetail = item
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
            }
        }
    }
}