package com.revbase.zaidanarrafif.domain.use_case.post_hafalan_use_case

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.domain.repositories.HafalanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class PostHafalanUseCase @Inject constructor(
    private val repository: HafalanRepository
) {
    operator fun invoke(token: String, nis: Int, surahName: String, note: String, recording: File): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val postStatus = repository.postHafalan(
                token = token,
                nis = nis,
                surah = surahName,
                catatan = note,
                file = recording
            ).success
            emit(Resource.Success<Boolean>(data = postStatus))
        } catch (e: HttpException) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<Boolean>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}