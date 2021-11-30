package com.revbase.zaidanarrafif.presentation.student.profile_screen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponseDTO

data class LogoutState(
    val isLoading: Boolean = false,
    val logoutCode: Int? = null,
    val error: String = ""
)