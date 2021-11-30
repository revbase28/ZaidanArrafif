package com.revbase.zaidanarrafif.data.remote.zaidan.dto

data class LoginGuruResponse(
    val data: LoginGuruResponseDTO,
    val message: String,
    val success: Boolean,
    val errors : String = ""
)