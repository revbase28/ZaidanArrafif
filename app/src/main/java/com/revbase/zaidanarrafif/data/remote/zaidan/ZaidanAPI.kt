package com.revbase.zaidanarrafif.data.remote.zaidan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ZaidanAPI {

    @FormUrlEncoded
    @POST("auth/siswa")
    suspend fun loginSiswa(
        @Field("username") username: String,
        @Field("password") password: String
    ) : LoginSiswaResponse

    @FormUrlEncoded
    @POST("auth/guru")
    suspend fun loginGuru(
        @Field("username") username: String,
        @Field("password") password: String
    ) : LoginGuruResponse

    @POST("logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ): Response<Any>

    @GET("kegiatan?jenis=kegiatan")
    suspend fun getAllDailyActivityJournal():JournalResponse

    @GET("kegiatan?jenis=ibadah")
    suspend fun getAllDailyWorshipJournal():JournalResponse

    @GET("siswa/{nis}/hafalan")
    suspend fun getHafalan(
        @Path ("nis") nis: Int,
        @Header ("Authorization") token: String
    ): GetHafalanResponse

    @Multipart
    @Headers("Accept: application/json")
    @POST("siswa/{nis}/hafalan")
    suspend fun postHafalan(
        @Path ("nis") nis: Int,
        @Header ("Authorization") token: String,
        @Part("surat") surah: RequestBody,
        @Part("catatan") catatan: RequestBody,
        @Part file: MultipartBody.Part
    ): PostHafalanResponse
}