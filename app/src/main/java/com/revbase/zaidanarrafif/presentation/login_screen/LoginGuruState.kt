package com.revbase.zaidanarrafif.presentation.login_screen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponseDTO
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponseDTO

data class LoginGuruState(
    val isLoading: Boolean = false,
    val loginData: LoginGuruResponseDTO? = null,
    val error: String = ""
)