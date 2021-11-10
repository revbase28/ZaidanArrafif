package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.StudentActivityResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.JournalSummaryResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.TeacherActivitySummaryResponse
import com.revbase.zaidanarrafif.domain.repositories.DailyJournalRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DailyJournalRepoImpl @Inject constructor(
    private val api:ZaidanAPI

): DailyJournalRepository{
    override suspend fun getAllDailyActivityJournal(token:String):StudentActivityResponse{
        return api.getAllDailyActivityJournal(token)
    }

    override suspend fun getAllDailyWorshipJournal(token: String): StudentActivityResponse {
        return api.getAllDailyWorshipJournal(token)
    }

    override suspend fun createDailyJournal(token: String, kegiatan: List<Int>, nis: Int):JournalResponse {
        val currentDate = Date()
        val formatedDate = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(currentDate)
         return api.createJournal( token = token , nis =  nis, kegiatan = kegiatan, date = formatedDate)
    }

    override suspend fun getTodaysJournal(token:String, nis:Int, jenis:String?):JournalResponse{
        return api.getTodaysJournal(token = token, nis = nis, jenis = jenis)
    }

    override suspend fun getJournalSummary(nip: Int, token: String, jenis: String?, date: String?): JournalSummaryResponse {
        return api.getJournalSummary(nip, token, jenis, date)
    }

    override suspend fun getActivityDetailInJournalSummary(
        nip: Int,
        activityId: Int,
        token: String,
        date: String?
    ): TeacherActivitySummaryResponse {
        return api.getActivityDetailInJournalSummary(nip, activityId, token, date)
    }

}