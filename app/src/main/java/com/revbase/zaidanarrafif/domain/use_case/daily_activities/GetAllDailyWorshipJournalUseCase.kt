package com.revbase.zaidanarrafif.domain.use_case.daily_activities

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

class GetAllDailyWorshipJournalUseCase @Inject constructor(
    private val repository: DailyJournalRepository
) {
    operator fun invoke(token:String):Flow<Resource<List<StudentActivity>>> = flow{
        Log.d("GetAllDailyWJournalUC", "GetAllDailyWJournalUC  executed")
        try {
            emit(Resource.Loading<List<StudentActivity>>())
            val allDailyWorshipJournal = repository.getAllDailyWorshipJournal(token).data.map { DailyJournalDTO->
                DailyJournalDTO.toStudentActivity()
            }
            Log.d("get_all_W_journalUC", "get all W journal executed")
            Log.d("allDailyWJournal", "$allDailyWorshipJournal")
            emit(Resource.Success<List<StudentActivity>>(data = allDailyWorshipJournal))
        }
        catch (e:HttpException)
        {
            emit(Resource.Error<List<StudentActivity>>(e.message?:"Failed to fetch data"))
        }
        catch (e: IOException) {
            emit(Resource.Error<List<StudentActivity>>("IOException: $e"))
        }
    }

}