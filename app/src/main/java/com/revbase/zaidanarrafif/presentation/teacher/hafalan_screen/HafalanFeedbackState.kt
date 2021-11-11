package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO

data class HafalanFeedbackState (
    val isLoading: Boolean = false,
    val hafalan: HafalanDTO? = null,
    val error: String = ""
)