package com.revbase.zaidanarrafif.presentation.student.hafalan_screen

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.domain.models.Surah

data class HafalanState(
    val isLoading: Boolean = false,
    val listHafalan: List<HafalanDTO> = emptyList(),
    val error: String = ""
)