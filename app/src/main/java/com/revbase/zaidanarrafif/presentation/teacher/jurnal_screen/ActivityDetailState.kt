package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.TeacherActivitySummary

data class ActivityDetailState(
    val isLoading: Boolean = false,
    val activityDetail: TeacherActivitySummary = TeacherActivitySummary(null),
    val error: String = ""
)
