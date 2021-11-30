package com.revbase.zaidanarrafif.presentation.student.rank_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import com.revbase.zaidanarrafif.domain.use_case.get_leaderboard.GetLeaderboardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val getLeaderboardUseCase: GetLeaderboardUseCase,
    private val preferenceManager: PreferenceManager,
): ViewModel() {
    private var _token = ""
    var student: Siswa? = null
    private val _state = mutableStateOf(RankState())
    val state: State<RankState> = _state

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token = it
            }
        }
        viewModelScope.launch {
            preferenceManager.getStudentDataFromPreferences().collect {
                student = it
            }
        }
    }

    fun getLeaderboard() {
        student?.let {
            getLeaderboardUseCase(it.nis, "Bearer $_token").onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = RankState(leaderboard = result.data)
                        Log.d("LeaderVM", result.data.toString())
                    }
                    is Resource.Error -> {
                        _state.value = RankState(error = result.message ?: "Failed to fetch data")
                    }
                    is Resource.Loading -> {
                        _state.value = RankState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}