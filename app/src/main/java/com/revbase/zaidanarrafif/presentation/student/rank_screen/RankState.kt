package com.revbase.zaidanarrafif.presentation.student.rank_screen

import com.revbase.zaidanarrafif.domain.models.Leaderboard

data class RankState(
    val isLoading: Boolean = false,
    val leaderboard: Leaderboard? = null,
    val error: String = ""
)