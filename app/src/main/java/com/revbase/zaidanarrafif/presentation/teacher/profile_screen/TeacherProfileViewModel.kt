package com.revbase.zaidanarrafif.presentation.teacher.profile_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.logout.LogoutUseCase
import com.revbase.zaidanarrafif.presentation.student.profile_screen.LogoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel() {

    private var _token = ""
    private val _logoutState = mutableStateOf(LogoutState())
    val logoutState = _logoutState

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token = it
            }
        }
    }

    fun logout() {
        logoutUseCase("Bearer $_token").onEach { result ->
            when(result){
                is Resource.Success -> {
                    preferenceManager.cleanPref()
                    _logoutState.value = LogoutState(logoutCode = result.data)
                }
                is Resource.Error -> {
                    _logoutState.value =
                        LogoutState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _logoutState.value = LogoutState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}