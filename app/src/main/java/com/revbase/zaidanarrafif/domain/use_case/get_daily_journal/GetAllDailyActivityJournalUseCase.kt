package com.revbase.zaidanarrafif.domain.use_case.get_daily_journal

import android.util.Log
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.toStudentActivity
import com.revbase.zaidanarrafif.domain.models.StudentActivity
import com.revbase.zaidanarrafif.domain.repositories.DailyJournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllDailyActivityJournalUseCase @Inject constructor(
    private val repository: DailyJournalRepository
) {
    operator fun invoke(token:String):Flow<Resource<List<StudentActivity>>> = flow{
        Log.d("GetAllDailyActivityJournalUC", "GetAllDailyActivityJournalUC  executed")
        try {
            emit(Resource.Loading<List<StudentActivity>>())
            val allDailyActivityJournal = repository.getAllDailyActivityJournal(token).data.map { DailyActivityDTO->
                DailyActivityDTO.toStudentActivity()
            }
            Log.d("get_all_A_journalUC", "get all journal executed")
            Log.d("allDailyAJournal", "$allDailyActivityJournal")
            emit(Resource.Success<List<StudentActivity>>(data = allDailyActivityJournal))
        }
        catch (e:HttpException)
        {
            emit(Resource.Error<List<StudentActivity>>(e.localizedMessage?:"Failed to fetch data"))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<StudentActivity>>("$e"))
        }
    }

}