package com.revbase.zaidanarrafif.data.remote.zaidan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.JournalSummaryResponse
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

    @Headers("Accept: application/json")
    @GET("guru/{nip}/jurnal")
    suspend fun getJournalSummary(
        @Path("nip") nip: Int,
        @Header("Authorization") token: String,
        @Query("jenis") jenis: String? = null,
        @Query("date") date: String? = null
    ): JournalSummaryResponse
}