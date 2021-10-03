package com.revbase.zaidanarrafif.data.remote.dto

data class SurahDetailResponse(
    val code: Int,
    val data: SurahDetailDTO,
    val message: String,
    val status: String
)