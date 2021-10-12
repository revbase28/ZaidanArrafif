package com.revbase.zaidanarrafif.presentation.student.quran_screen

import com.revbase.zaidanarrafif.domain.models.SurahDetail

data class DownloadState(
    val isLoading: Boolean = false,
    val data: Int? = null,
    val error: String = ""
)