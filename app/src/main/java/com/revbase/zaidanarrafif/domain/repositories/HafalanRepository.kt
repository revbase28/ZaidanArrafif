package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetHafalanResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.PostHafalanResponse
import java.io.File

interface HafalanRepository {

    suspend fun getHafalan(token: String, nis: Int): GetHafalanResponse

    suspend fun postHafalan(token: String, nis: Int, surah: String, catatan: String, file: File): PostHafalanResponse
}