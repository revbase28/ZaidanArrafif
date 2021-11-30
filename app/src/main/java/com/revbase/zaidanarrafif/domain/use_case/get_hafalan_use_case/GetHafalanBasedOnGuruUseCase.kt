package com.revbase.zaidanarrafif.domain.use_case.get_hafalan_use_case

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.domain.repositories.HafalanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHafalanBasedOnGuruUseCase @Inject constructor(
    private val repository: HafalanRepository
) {
    operator fun invoke(nip: Int, token: String): Flow<Resource<List<HafalanDTO>>> = flow {
        try {
            emit(Resource.Loading<List<HafalanDTO>>())
            val dataHafalan = repository.getHafalanBasedOnGuru(token = "Bearer $token", nip = nip).data
            emit(Resource.Success<List<HafalanDTO>>(data = dataHafalan))
        } catch (e: HttpException) {
            emit(Resource.Error<List<HafalanDTO>>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<List<HafalanDTO>>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}