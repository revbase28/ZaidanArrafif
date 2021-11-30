package com.revbase.zaidanarrafif.domain.use_case.get_student

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.StudentRepoImpl
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStudentBasedOnTeacherUseCase @Inject constructor(
    private val repository: StudentRepoImpl
) {
    operator fun invoke(nip: Int, token: String): Flow<Resource<List<Siswa>>> = flow {
        try {
            emit(Resource.Loading<List<Siswa>>())
            val studentList = repository.getStudentBasedOnTeacher(nip, "Bearer $token").data
            emit(Resource.Success<List<Siswa>>(data = studentList))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Siswa>>(e.localizedMessage ?: "Terjadi kesalahan tidak terduga"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Siswa>>("Tidak bisa terhubung ke server, coba periksa koneksi internet"))
        }
    }
}