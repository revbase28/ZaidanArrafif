package com.revbase.zaidanarrafif.presentation.teacher.ListStudentScreen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa

data class StudentState(
    val isLoading: Boolean = false,
    val studentList: List<Siswa> = emptyList(),
    val error: String = ""
)