package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse

interface JournalRepository {
    suspend fun getAllDailyActivityJournal(token:String):JournalResponse

    suspend fun getAllDailyWorshipJournal(token:String):JournalResponse
}