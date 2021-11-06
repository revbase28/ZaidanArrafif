package com.revbase.zaidanarrafif.presentation.login_screen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponseDTO

data class LoginSiswaState(
    val isLoading: Boolean = false,
    val loginData: LoginSiswaResponseDTO? = null,
    val error: String = ""
)