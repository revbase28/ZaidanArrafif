package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.StudentActivityResponse

interface DailyJournalRepository {
    suspend fun getAllDailyActivityJournal(token:String):StudentActivityResponse

    suspend fun getAllDailyWorshipJournal(token:String):StudentActivityResponse

    suspend fun  createDailyJournal(token:String, kegiatan:List<Int>,  nis:Int):JournalResponse
}