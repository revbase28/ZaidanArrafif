package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetHafalanResponse

interface HafalanRepository {

    suspend fun getHafalan(token: String, nis: Int): GetHafalanResponse
}