package com.revbase.zaidanarrafif.data.remote.zaidan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.StudentActivityResponse
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
    suspend fun getAllDailyActivityJournal(
        @Header("Authorization")token: String
    ):StudentActivityResponse

    @GET("kegiatan?jenis=ibadah")
    suspend fun getAllDailyWorshipJournal(
        @Header("Authorization")token: String
    ):StudentActivityResponse


    @FormUrlEncoded
    @POST("siswa/{nis}/jurnal")
    suspend fun createJournal(
        @Header("Authorization")token:String,
        @Field("kegiatan[]") kegiatan:List<Int> ,
        @Field("date")date:String,
        @Path("nis") nis: Int,
        @Header("Accept")accept:String = "application/json"
    ):JournalResponse


    @GET("siswa/{nis}/jurnal/today")
    suspend fun getTodaysJournal(
        @Header("Authorization")token: String,
        @Header("Accept")accept: String = "application/json",
        @Path("nis")nis:Int,
        @Query("jenis")jenis:String?
    ):JournalResponse

}