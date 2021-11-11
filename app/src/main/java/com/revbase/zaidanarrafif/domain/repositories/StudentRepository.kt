package com.revbase.zaidanarrafif.domain.repositories

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.GetSiswaResponse

interface StudentRepository {
    suspend fun getStudentBasedOnTeacher(nip: Int, token: String): GetSiswaResponse
}