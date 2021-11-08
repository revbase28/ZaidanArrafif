package com.revbase.zaidanarrafif.domain.use_case.get_leaderboard

import android.util.Log
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.toLeaderboard
import com.revbase.zaidanarrafif.domain.models.Leaderboard
import com.revbase.zaidanarrafif.domain.repositories.LeaderboardRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLeaderboardUseCase @Inject constructor(
    private val repository: LeaderboardRepository
) {
    operator fun invoke(nis: Int, token: String): Flow<Resource<Leaderboard>> = flow {
        try {
            emit(Resource.Loading<Leaderboard>())
            val leaderboard = repository.getLeaderboard(nis, token).data.toLeaderboard()
            Log.d("GetLeaderboard", "$leaderboard")
            emit(Resource.Success<Leaderboard>(data = leaderboard))
        } catch (e: HttpException) {
            emit(Resource.Error<Leaderboard>(e.localizedMessage ?: "Failed to fetch data"))
            Log.e("LeadUC", "${e.response()?.errorBody()}")
        } catch (e: IOException) {
            emit(Resource.Error<Leaderboard>("$e"))
        }
    }
}