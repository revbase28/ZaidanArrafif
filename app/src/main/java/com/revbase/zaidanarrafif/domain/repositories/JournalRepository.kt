package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse

interface JournalRepository {
    suspend fun getAllDailyJournal():JournalResponse
}