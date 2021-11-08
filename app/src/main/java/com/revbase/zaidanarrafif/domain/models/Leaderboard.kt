package com.revbase.zaidanarrafif.domain.models

data class Leaderboard(
    val studentRank: Int,
    val stars: Int,
    val leaderboard: List<LeaderboardStudentDetail>
)

data class LeaderboardStudentDetail(
    val name: String,
    val stars: Int,
)
