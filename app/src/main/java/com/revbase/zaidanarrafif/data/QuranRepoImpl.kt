package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.QuranAPI
import com.revbase.zaidanarrafif.data.remote.dto.AllSurahResponse
import com.revbase.zaidanarrafif.data.remote.dto.SurahDetailResponse
import com.revbase.zaidanarrafif.domain.repositories.QuranRepository
import javax.inject.Inject

class QuranRepoImpl @Inject constructor(
    private val api: QuranAPI
): QuranRepository {
    override suspend fun getAllSurah(): AllSurahResponse {
        return api.getAllSurah()
    }

    override suspend fun getSurahDetail(surahNumber: Int): SurahDetailResponse {
        return api.getSurah(surahNumber)
    }
}