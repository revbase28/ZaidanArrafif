package com.revbase.zaidanarrafif.domain.use_case.logins

import android.util.Log
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponseDTO
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponseDTO
import com.revbase.zaidanarrafif.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginGuruUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val preferenceManager: PreferenceManager
) {

    operator fun invoke(username: String, password: String): Flow<Resource<LoginGuruResponseDTO>> = flow {
        try {
            emit(Resource.Loading<LoginGuruResponseDTO>())
            val loginData = repository.loginGuru(username, password)
            if(loginData.success){
                emit(Resource.Success<LoginGuruResponseDTO>(data = loginData.data))
                preferenceManager.storeTeacherData(loginData.data.user, loginData.data.token)
            } else
                emit(Resource.Error<LoginGuruResponseDTO>(message = loginData.errors))
        } catch (e: HttpException) {
            emit(Resource.Error<LoginGuruResponseDTO>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<LoginGuruResponseDTO>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}