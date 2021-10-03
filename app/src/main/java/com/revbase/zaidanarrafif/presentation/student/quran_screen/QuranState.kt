package com.revbase.zaidanarrafif.presentation.student.quran_screen

import com.revbase.zaidanarrafif.domain.models.Surah

data class QuranState(
    val isLoading: Boolean = false,
    val listSurah: List<Surah> = emptyList(),
    val error: String = ""
)