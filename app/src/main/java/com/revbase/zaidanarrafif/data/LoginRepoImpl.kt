package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginGuruResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.LoginSiswaResponse
import com.revbase.zaidanarrafif.domain.repositories.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val zaidanAPI: ZaidanAPI
): LoginRepository {

    override suspend fun loginSiswa(username: String, password: String): LoginSiswaResponse {
        return zaidanAPI.loginSiswa(username, password)
    }

    override suspend fun loginGuru(username: String, password: String): LoginGuruResponse {
        return zaidanAPI.loginGuru(username, password)
    }

    override suspend fun logout(token: String): Response<Any> {
        return zaidanAPI.logout(token)
    }
}