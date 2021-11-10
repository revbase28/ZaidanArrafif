package com.revbase.zaidanarrafif.data.remote.zaidan.dto


data class StudentActivityResponse(
    val success: Boolean,
    val message: String,
    val data: List<StudentActivityDTO>
)
