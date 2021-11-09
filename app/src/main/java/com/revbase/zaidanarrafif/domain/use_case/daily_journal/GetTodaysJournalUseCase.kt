package com.revbase.zaidanarrafif.domain.use_case.daily_journal

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.toJournal
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.models.StudentActivity
import com.revbase.zaidanarrafif.domain.repositories.DailyJournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTodaysJournalUseCase @Inject constructor(
    private val repository:DailyJournalRepository

) {
    operator  fun invoke(token:String,nis:Int, jenis:String?=null):Flow<Resource<Journal>> = flow {
        try {

            emit(Resource.Loading<Journal>())
            val todaysJournal = repository.getTodaysJournal(token,nis,jenis).data.toJournal()
            emit(Resource.Success<Journal>(data = todaysJournal))
        }
        catch (e:HttpException)
        {
            emit(Resource.Error<Journal>(e.localizedMessage?:"Failed to fetch data"))
        }
        catch (e: IOException) {
            emit(Resource.Error<Journal>("$e"))
        }
    }
}