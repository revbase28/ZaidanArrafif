package com.revbase.zaidanarrafif.data.remote.zaidan.dto

data class LoginSiswaResponse(
    val data: LoginSiswaResponseDTO,
    val message: String,
    val errors: String = "",
    val success: Boolean
)