package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import com.revbase.zaidanarrafif.domain.models.Leaderboard
import com.revbase.zaidanarrafif.domain.models.LeaderboardStudentDetail

data class LeaderboardDTO(
    val peringkat_siswa: Int,
    val stars: Int,
    val leaderboard: List<Siswa>
)

fun LeaderboardDTO.toLeaderboard(): Leaderboard {
    return Leaderboard(
        studentRank = peringkat_siswa,
        stars = stars,
        leaderboard = leaderboard.map {
            LeaderboardStudentDetail(name = it.nama_siswa, stars = it.stars)
        }
    )
}
