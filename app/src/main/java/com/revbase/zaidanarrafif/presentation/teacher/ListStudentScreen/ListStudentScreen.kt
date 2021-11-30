package com.revbase.zaidanarrafif.presentation.teacher.ListStudentScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.common_component.LoadingScreen
import com.revbase.zaidanarrafif.presentation.teacher.ListStudentScreen.component.StudentItem

@Composable
fun ListStudentScreen(
    viewModel: StudentListViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getStudent()
    }
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp, top = 24.dp)
    ) {
        Text(
            text = "Daftar Siswa",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

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
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(state.studentList) {index, item ->
                    StudentItem(
                        index = index,
                        studentName = item.nama_siswa,
                        studentStars = item.stars
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}