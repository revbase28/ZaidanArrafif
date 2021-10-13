package com.revbase.zaidanarrafif.data.remote.quran

import com.revbase.zaidanarrafif.data.remote.quran.dto.AllSurahResponse
import com.revbase.zaidanarrafif.data.remote.quran.dto.SurahDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranAPI {

    @GET("surah")
    suspend fun getAllSurah(): AllSurahResponse

    @GET("surah/{surah_id}")
    suspend fun getSurah(
        @Path("surah_id") surahId: Int
    ): SurahDetailResponse
}