package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetHafalanResponse
import com.revbase.zaidanarrafif.domain.repositories.HafalanRepository
import javax.inject.Inject

class HafalanRepoImpl @Inject constructor(
    private val zaidanAPI: ZaidanAPI
): HafalanRepository {

    override suspend fun getHafalan(token: String, nis: Int): GetHafalanResponse {
        return zaidanAPI.getHafalan(nis, token)
    }
}