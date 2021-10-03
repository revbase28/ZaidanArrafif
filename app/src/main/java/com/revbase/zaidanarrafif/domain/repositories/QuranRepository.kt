package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.dto.AllSurahResponse
import com.revbase.zaidanarrafif.data.remote.dto.SurahDetailResponse

interface QuranRepository {

    suspend fun getAllSurah(): AllSurahResponse

    suspend fun getSurahDetail(surahNumber: Int): SurahDetailResponse
}