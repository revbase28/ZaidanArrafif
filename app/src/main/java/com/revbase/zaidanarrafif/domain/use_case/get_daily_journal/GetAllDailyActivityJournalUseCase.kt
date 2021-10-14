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

class GetAllDailyActivityJournalUseCase @Inject constructor(
    private val repository: JournalRepository
) {
    operator fun invoke():Flow<Resource<List<Journal>>> = flow{
        Log.d("GetAllDailyActivityJournalUC", "GetAllDailyActivityJournalUC  executed")
        try {
            emit(Resource.Loading<List<Journal>>())
            val allDailyActivityJournal = repository.getAllDailyActivityJournal().data.map { DailyActivityDTO->
                DailyActivityDTO.toJournal()
            }
            Log.d("get_all_A_journalUC", "get all journal executed")
            Log.d("allDailyAJournal", "$allDailyActivityJournal")
            emit(Resource.Success<List<Journal>>(data = allDailyActivityJournal))
        }
        catch (e:HttpException)
        {
            emit(Resource.Error<List<Journal>>(e.localizedMessage?:"Failed to fetch data"))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<Journal>>("$e"))
        }
    }

}