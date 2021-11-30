package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponse
import retrofit2.Response

interface LoginRepository {

    suspend fun loginSiswa(username: String, password: String): LoginSiswaResponse

    suspend fun loginGuru(username: String, password: String): LoginGuruResponse

    suspend fun logout(token: String): Response<Any>

}