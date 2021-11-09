package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.JournalResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.JournalSummaryResponse
import com.revbase.zaidanarrafif.domain.repositories.JournalRepository
import javax.inject.Inject

class JournalRepoImpl @Inject constructor(
    private val api:ZaidanAPI

): JournalRepository{
    override suspend fun getAllDailyActivityJournal():JournalResponse{
        return api.getAllDailyActivityJournal()
    }

    override suspend fun getAllDailyWorshipJournal(): JournalResponse {
        return api.getAllDailyWorshipJournal()
    }

    override suspend fun getJournalSummary(nip: Int, token: String, jenis: String?, date: String?): JournalSummaryResponse {
        return api.getJournalSummary(nip, token, jenis, date)
    }
}