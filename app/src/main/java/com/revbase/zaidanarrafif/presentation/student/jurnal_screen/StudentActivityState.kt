package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import com.revbase.zaidanarrafif.domain.models.StudentActivity

data class StudentActivityState (
    val  isLoading: Boolean = false,
    val  studentActivityList: List<StudentActivity> = emptyList(),
    val error: String = ""

)