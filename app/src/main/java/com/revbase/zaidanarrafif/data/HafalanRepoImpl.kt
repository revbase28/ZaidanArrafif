package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetHafalanResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.PostHafalanResponse
import com.revbase.zaidanarrafif.domain.repositories.HafalanRepository
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class HafalanRepoImpl @Inject constructor(
    private val zaidanAPI: ZaidanAPI
): HafalanRepository {

    override suspend fun getHafalan(token: String, nis: Int): GetHafalanResponse {
        return zaidanAPI.getHafalan(nis, token)
    }

    override suspend fun postHafalan(
        token: String,
        nis: Int,
        surah: String,
        catatan: String,
        file: File
    ): PostHafalanResponse {
        val surahName = surah.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val catatan = catatan.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val recordingFile = file.asRequestBody("audio/mp3".toMediaTypeOrNull())
        val fileBody = MultipartBody.Part.createFormData("file", file.name, recordingFile)
        return zaidanAPI.postHafalan(nis, token, surahName, catatan, fileBody)
    }

}