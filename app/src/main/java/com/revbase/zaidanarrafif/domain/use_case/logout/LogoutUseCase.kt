package com.revbase.zaidanarrafif.domain.use_case.logout

import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Constant.LOGOUT_SUCCESS
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponseDTO
import com.revbase.zaidanarrafif.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(token: String) = flow<Resource<Int>> {
        try {
            emit(Resource.Loading<Int>())
            val response = repository.logout(token)
            if(response.code() == LOGOUT_SUCCESS)
                emit(Resource.Success(response.code()))
            else
                emit(Resource.Error("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        } catch (e: HttpException) {
            emit(Resource.Error<Int>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<Int>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}