package com.revbase.zaidanarrafif.data.remote.quran.dto

data class AllSurahResponse(
    val code: Int,
    val data: List<SurahDTO>,
    val message: String,
    val status: String
)