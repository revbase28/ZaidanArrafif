package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.TableCell
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.TableHeader

@Composable
fun TeacherHafalanScreen(
    navController: NavController,
    viewModel: TeacherHafalanViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getHafalan()
    }
    val state = viewModel.hafalanState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
    ) {
        Text(
            text = "Hafalan Siswa Yang Belum Diperiksa",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp
        ) {
            if (state.isLoading) {
                LoadingScreen(modifier = Modifier.fillMaxSize())
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
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)) {
                        Column(
                            modifier = Modifier.weight(0.1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Divider(modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp),
                                color = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier.weight(0.3f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Divider(modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp),
                                color = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier.weight(0.3f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Divider(modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp),
                                color = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier.weight(0.3f),
                            horizontalAlignment = Alignment.End
                        ) {

                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp),
                    ) {
                        TableHeader()
                        Divider(thickness = 1.dp, color = Color.Black)
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(state.listHafalan) { index, item ->
                                TableCell(
                                    index = index,
                                    studentName = item.siswa.nama_siswa,
                                    surahName = item.surat,
                                    onButtonClick = {
                                        navController.currentBackStackEntry
                                            ?.arguments
                                            ?.putParcelable("hafalanData", item)
                                        navController.navigate(Screen.HafalanFeedbackScreen.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}