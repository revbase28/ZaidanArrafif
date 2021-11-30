package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LeaderboardResponse
import com.revbase.zaidanarrafif.domain.repositories.LeaderboardRepository
import javax.inject.Inject

class LeaderboardRepoImpl @Inject constructor(
    private val api: ZaidanAPI
): LeaderboardRepository{
    override suspend fun getLeaderboard(nis: Int, token: String): LeaderboardResponse {
        return api.getLeaderboard(nis, token)
    }
}