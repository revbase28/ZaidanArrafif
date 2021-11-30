package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Tools
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.common_component.ScreenTitleBar

@ExperimentalCoilApi
@Composable
fun ActivityDetail(
    navController: NavController,
    activityId: Int,
    date: String?,
    viewModel: TeacherJournalViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getActivityDetail(activityId, date)
    }
    val state = viewModel.activityState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp, top = 14.dp)
    ) {
        val textStyle = TextStyle(
            color = Color.Black,
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        ScreenTitleBar(
            screenTitle = "Detail Kegiatan",
            navController = navController,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingScreen()
            }
        } else if (state.error.isNotBlank()) {
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
                text = "Jurnal ${state.activityDetail.kegiatan?.jenis} " +
                        "Tanggal ${date?.let { Tools.dateTimeStringToString(it) }}",
                style = textStyle
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Total yang mengerjakan: ${state.activityDetail.siswa.size} siswa",
                style = textStyle
            )

            Spacer(modifier = Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    state.activityDetail.kegiatan?.let {
                        Text(
                            text = it.deskripsi,
                            style = TextStyle(
                                fontFamily = Constant.LATO_FONT_FAMILY,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.fillMaxWidth(0.7f)
                        )
                    }
                    Image(painter = rememberImagePainter(
                        data = state.activityDetail.kegiatan?.url_icon
                    ),
                        contentDescription = null,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(items = state.activityDetail.siswa) {index, item ->
                    Text(
                        text = "${index + 1}. ${item.nama_siswa}",
                        style = TextStyle(
                            fontFamily = Constant.LATO_FONT_FAMILY,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                    Divider()
                }
            }
        }
    }
}