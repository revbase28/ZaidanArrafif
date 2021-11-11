package com.revbase.zaidanarrafif.data.remote.zaidan.dto

data class GetSiswaResponse(
    val success: Boolean,
    val message: String,
    val data: List<Siswa>
)
