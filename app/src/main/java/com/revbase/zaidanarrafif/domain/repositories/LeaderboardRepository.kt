package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LeaderboardResponse

interface LeaderboardRepository {
    suspend fun getLeaderboard(nis: Int, token: String): LeaderboardResponse
}