package com.revbase.zaidanarrafif.data

import com.revbase.zaidanarrafif.data.remote.zaidan.ZaidanAPI
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetSiswaResponse
import com.revbase.zaidanarrafif.domain.repositories.StudentRepository
import javax.inject.Inject

class StudentRepoImpl @Inject constructor(
    private val zaidanAPI: ZaidanAPI
): StudentRepository {
    override suspend fun getStudentBasedOnTeacher(nip: Int, token: String): GetSiswaResponse {
        return zaidanAPI.getStudentBasedOnTeacher(nip, token)
    }
}