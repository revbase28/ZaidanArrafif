package com.revbase.zaidanarrafif.data.remote.zaidan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ZaidanAPI {

    @GET("kegiatan?jenis=kegiatan")

    suspend fun getAllDailyJournal():JournalResponse
}