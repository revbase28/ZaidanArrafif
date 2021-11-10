package com.revbase.zaidanarrafif.domain.use_case.get_journal_summary

import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher.TeacherActivitySummary
import com.revbase.zaidanarrafif.domain.repositories.DailyJournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetActivityDetailInJournalSummaryUseCase @Inject constructor(
    private val repository: DailyJournalRepository
) {
    operator fun invoke(nip: Int, activityId: Int, token: String, date: String?): Flow<Resource<TeacherActivitySummary>> = flow {
        try {
            emit(Resource.Loading<TeacherActivitySummary>())
            val activitySummary = repository
                .getActivityDetailInJournalSummary(nip, activityId, token, date)
                .data
            emit(Resource.Success<TeacherActivitySummary>(data = activitySummary))
        } catch (e: HttpException) {
            emit(Resource.Error<TeacherActivitySummary>(e.localizedMessage?: "Failed to fetch data"))
        } catch (e: IOException) {
            emit(Resource.Error<TeacherActivitySummary>("e"))
        }
    }
}