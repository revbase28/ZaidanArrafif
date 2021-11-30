package com.revbase.zaidanarrafif.data.remote.zaidan.dto

data class GetHafalanResponse(
    val data: List<HafalanDTO>,
    val message: String,
    val success: Boolean
)