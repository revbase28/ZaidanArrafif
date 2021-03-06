package com.revbase.zaidanarrafif.domain.use_case.get_journal_summary

import android.util.Log
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.toJournalSummary
import com.revbase.zaidanarrafif.domain.models.JournalSummary
import com.revbase.zaidanarrafif.domain.repositories.DailyJournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetJournalSummaryUseCase @Inject constructor(
    private val repository: DailyJournalRepository
) {
    operator fun invoke(nip: Int, token: String, jenis: String?, date: String?): Flow<Resource<List<JournalSummary>>> = flow{
        Log.d("GetJournalSummaryUC", "GetJournalSummaryUC  executed")
        try {
            emit(Resource.Loading<List<JournalSummary>>())
            val journalSummary = repository.getJournalSummary(nip, token, jenis, date).data.map { JournalSummaryDTO ->
                JournalSummaryDTO.toJournalSummary()
            }
            Log.d("get_summary_journalUC", "get journal summary executed")
            Log.d("JournalSummary", "$journalSummary")
            emit(Resource.Success<List<JournalSummary>>(data = journalSummary))
        }
        catch (e: HttpException)
        {
            emit(Resource.Error<List<JournalSummary>>(e.localizedMessage?:"Failed to fetch data"))
            Log.e("Get error", e.response()?.headers().toString())
        }
        catch (e: IOException) {
            emit(Resource.Error<List<JournalSummary>>("$e"))
        }
    }
}