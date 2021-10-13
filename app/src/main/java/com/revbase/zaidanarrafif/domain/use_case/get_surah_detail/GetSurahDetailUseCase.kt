package com.revbase.zaidanarrafif.domain.use_case.get_surah_detail

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.quran.dto.toSurahDetail
import com.revbase.zaidanarrafif.domain.models.SurahDetail
import com.revbase.zaidanarrafif.domain.repositories.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSurahDetailUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(surahNumber: Int): Flow<Resource<SurahDetail>> = flow {
        try {
            emit(Resource.Loading<SurahDetail>())
            val surah = repository.getSurahDetail(surahNumber).data.toSurahDetail()
            emit(Resource.Success<SurahDetail>(surah))
        } catch (e: HttpException) {
            emit(Resource.Error<SurahDetail>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<SurahDetail>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}