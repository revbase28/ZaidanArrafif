package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.JournalSummaryResponse

interface JournalRepository {
    suspend fun getAllDailyActivityJournal():JournalResponse

    suspend fun getAllDailyWorshipJournal():JournalResponse

    suspend fun getJournalSummary(
        nip: Int,
        token: String,
        jenis: String?,
        date: String?
    ): JournalSummaryResponse
}