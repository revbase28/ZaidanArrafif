package com.revbase.zaidanarrafif.presentation.student.surah_screen

import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.models.SurahDetail

data class SurahState(
    val isLoading: Boolean = false,
    val data: SurahDetail? = null,
    val error: String = ""
)