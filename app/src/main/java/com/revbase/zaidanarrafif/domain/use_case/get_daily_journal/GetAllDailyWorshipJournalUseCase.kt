package com.revbase.zaidanarrafif.domain.use_case.get_daily_journal

import android.util.Log
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.toJournal
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.repositories.JournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllDailyWorshipJournalUseCase @Inject constructor(
    private val repository: JournalRepository
) {
    operator fun invoke():Flow<Resource<List<Journal>>> = flow{
        Log.d("GetAllDailyWJournalUC", "GetAllDailyWJournalUC  executed")
        try {
            emit(Resource.Loading<List<Journal>>())
            val allDailyWorshipJournal = repository.getAllDailyWorshipJournal().data.map { DailyJournalDTO->
                DailyJournalDTO.toJournal()
            }
            Log.d("get_all_W_journalUC", "get all W journal executed")
            Log.d("allDailyWJournal", "$allDailyWorshipJournal")
            emit(Resource.Success<List<Journal>>(data = allDailyWorshipJournal))
        }
        catch (e:HttpException)
        {
            emit(Resource.Error<List<Journal>>(e.message?:"Failed to fetch data"))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<Journal>>("IOException: $e"))
        }
    }

}