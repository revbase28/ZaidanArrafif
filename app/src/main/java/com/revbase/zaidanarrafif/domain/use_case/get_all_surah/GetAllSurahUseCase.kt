package com.revbase.zaidanarrafif.domain.use_case.get_all_surah

import android.util.Log
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.dto.toSurah
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.repositories.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetAllSurahUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(): Flow<Resource<List<Surah>>> = flow {
        try {
            emit(Resource.Loading<List<Surah>>())
            val allSurah = repository.getAllSurah().data.map { surahDTO ->
                surahDTO.toSurah()
            }
            Log.d("Use case", "get all surat executed")
            emit(Resource.Success<List<Surah>>(data = allSurah))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Surah>>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Surah>>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}