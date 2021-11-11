package com.revbase.zaidanarrafif.data.remote.zaidan

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.*
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.JournalSummaryResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.TeacherActivitySummaryResponse
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

    @Headers("Accept: application/json")
    @GET("guru/{nip}/jurnal")
    suspend fun getJournalSummary(
        @Path("nip") nip: Int,
        @Header("Authorization") token: String,
        @Query("jenis") jenis: String? = null,
        @Query("date") date: String? = null
    ): JournalSummaryResponse

    @Headers("Accept: application/json")
    @GET("guru/{nip}/jurnal/kegiatan/{id}/siswa")
    suspend fun getActivityDetailInJournalSummary(
        @Path("nip") nip: Int,
        @Path("id") activityId: Int,
        @Header("Authorization") token: String,
        @Query("date") date: String? = null
    ): TeacherActivitySummaryResponse
    suspend fun getAllDailyWorshipJournal():JournalResponse

    @GET("siswa/{nis}/hafalan")
    suspend fun getHafalan(
        @Path ("nis") nis: Int,
        @Header ("Authorization") token: String
    ): GetHafalanResponse

    @GET("guru/{nip}/hafalan")
    suspend fun getHafalanBasedOnGuru(
        @Path("nip") nip: Int,
        @Header("Authorization") token: String
    ): GetHafalanResponse

    @GET("guru/{nip}/siswa")
    suspend fun getStudentBasedOnTeacher(
        @Path("nip") nip: Int,
        @Header("Authorization") token: String
    ): GetSiswaResponse

    @FormUrlEncoded
    @PUT("hafalan/{id}")
    suspend fun updateHafalan(
        @Path("id") hafalanId: Int,
        @Header("Authorization") token: String,
        @Field("komentar") comment: String,
        @Field("star") star: Int = 0
    ): PostHafalanResponse
}