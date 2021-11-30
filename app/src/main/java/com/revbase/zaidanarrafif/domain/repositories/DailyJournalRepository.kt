package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.StudentActivityResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.JournalSummaryResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.TeacherActivitySummaryResponse

interface DailyJournalRepository {
    suspend fun getAllDailyActivityJournal(token:String):StudentActivityResponse

    suspend fun getAllDailyWorshipJournal(token:String):StudentActivityResponse

    suspend fun  createDailyJournal(token:String, kegiatan:List<Int>,  nis:Int):JournalResponse

    suspend fun getTodaysJournal(token:String, nis:Int,jenis:String?):JournalResponse

    suspend fun getJournalSummary(
        nip: Int,
        token: String,
        jenis: String?,
        date: String?
    ): JournalSummaryResponse

    suspend fun getActivityDetailInJournalSummary(
        nip: Int,
        activityId: Int,
        token: String,
        date: String?
    ): TeacherActivitySummaryResponse
}