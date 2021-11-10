package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen.component.TableCell

@Composable
fun TeacherHafalanScreen(
    navController: NavController
) {
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No",
                            fontFamily = Constant.LATO_FONT_FAMILY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.1f),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Nama",
                            fontFamily = Constant.LATO_FONT_FAMILY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.3f),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Surat",
                            fontFamily = Constant.LATO_FONT_FAMILY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.3f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Komentar & Rekaman",
                            fontFamily = Constant.LATO_FONT_FAMILY,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.3f),
                            textAlign = TextAlign.Center
                        )
                    }
                    Divider(thickness = 1.dp, color = Color.Black)
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(15) {
                            TableCell(
                                index = 1,
                                onButtonClick = {
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