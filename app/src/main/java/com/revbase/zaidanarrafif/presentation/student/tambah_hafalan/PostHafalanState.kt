package com.revbase.zaidanarrafif.presentation.student.tambah_hafalan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.domain.models.Surah

data class PostHafalanState(
    val isLoading: Boolean = false,
    val isPostingSuccess: Boolean = false,
    val error: String = ""
)