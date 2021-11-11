package com.revbase.zaidanarrafif.domain.use_case.update_hafalan_use_case

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.domain.repositories.HafalanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateHafalanUseCase @Inject constructor(
    private val repository: HafalanRepository
) {
    operator fun invoke(hafalanId: Int, token: String, comment: String, star:Int = 0): Flow<Resource<HafalanDTO>> = flow {
        try {
            emit(Resource.Loading<HafalanDTO>())
            val response = repository.updateHafalan(hafalanId, "Bearer $token", comment, star)
            if (response.success) {
                emit(Resource.Success<HafalanDTO>(data = response.data))
            } else {
                emit(Resource.Error<HafalanDTO>(message = response.message))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<HafalanDTO>(message = e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<HafalanDTO>(message = "Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}