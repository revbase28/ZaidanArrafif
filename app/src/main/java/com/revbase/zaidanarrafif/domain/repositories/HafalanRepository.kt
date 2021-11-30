package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetHafalanResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.PostHafalanResponse
import java.io.File

interface HafalanRepository {

    suspend fun getHafalan(token: String, nis: Int): GetHafalanResponse

    suspend fun getHafalanBasedOnGuru(nip: Int, token: String): GetHafalanResponse

    suspend fun updateHafalan(hafalanId: Int, token: String, comment: String, star: Int): PostHafalanResponse

    suspend fun postHafalan(token: String, nis: Int, surah: String, catatan: String, file: File): PostHafalanResponse
}