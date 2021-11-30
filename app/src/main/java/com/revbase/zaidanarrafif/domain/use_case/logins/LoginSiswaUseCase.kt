package com.revbase.zaidanarrafif.domain.use_case.logins

import android.util.Log
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponseDTO
import com.revbase.zaidanarrafif.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginSiswaUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val preferenceManager: PreferenceManager
) {

    operator fun invoke(username: String, password: String): Flow<Resource<LoginSiswaResponseDTO>> = flow {
        try {
            emit(Resource.Loading<LoginSiswaResponseDTO>())
            val loginData = repository.loginSiswa(username, password)
            if(loginData.success){
                emit(Resource.Success<LoginSiswaResponseDTO>(data = loginData.data))
                preferenceManager.storeStudentData(loginData.data.user, loginData.data.token)
            } else
                emit(Resource.Error<LoginSiswaResponseDTO>(message = loginData.errors))
        } catch (e: HttpException) {
            emit(Resource.Error<LoginSiswaResponseDTO>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
            Log.e("LoginErr", e.response().toString())
        } catch (e: IOException) {
            emit(Resource.Error<LoginSiswaResponseDTO>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
            Log.e("LoginErr", e.toString())
        }
    }
}