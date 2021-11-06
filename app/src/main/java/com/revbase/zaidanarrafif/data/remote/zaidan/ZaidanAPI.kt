package com.revbase.zaidanarrafif.data.remote.zaidan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponse
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
}