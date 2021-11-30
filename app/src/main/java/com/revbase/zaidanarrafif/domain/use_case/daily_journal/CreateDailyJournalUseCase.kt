package com.revbase.zaidanarrafif.domain.use_case.daily_journal

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.toJournal
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.repositories.DailyJournalRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateDailyJournalUseCase @Inject constructor(
    private val repository: DailyJournalRepository
){
    operator fun invoke(token:String,kegiatan:List<Int>,  nis:Int)= flow<Resource<Journal>> {
        try {
            emit(Resource.Loading<Journal>())
            val response  = repository.createDailyJournal(token,kegiatan,nis)
            val journal = response.data!!.toJournal()
            if(response.success)
            {
                emit(Resource.Success<Journal>(data = journal))
            }
            else{
                emit(Resource.Error("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
            }
        }catch (e:HttpException){
            emit(Resource.Error<Journal>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))

        }catch (e: IOException) {
            emit(Resource.Error<Journal>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}