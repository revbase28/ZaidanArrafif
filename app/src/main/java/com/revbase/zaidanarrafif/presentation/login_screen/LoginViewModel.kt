package com.revbase.zaidanarrafif.presentation.login_screen

import android.app.Activity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.logins.LoginGuruUseCase
import com.revbase.zaidanarrafif.domain.use_case.logins.LoginSiswaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginSiswaUseCase: LoginSiswaUseCase,
    private val loginGuruUseCase: LoginGuruUseCase
): ViewModel() {

    private val _loginSiswaState = mutableStateOf(LoginSiswaState())
    val loginSiswaState: State<LoginSiswaState> = _loginSiswaState
    private val _loginGuruState = mutableStateOf(LoginGuruState())
    val loginGuruState: State<LoginGuruState> = _loginGuruState

    fun loginSiswa(username: String, password: String) {
        loginSiswaUseCase(username, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _loginSiswaState.value = LoginSiswaState(loginData = result.data)
                }
                is Resource.Error -> {
                    _loginSiswaState.value =
                        LoginSiswaState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _loginSiswaState.value = LoginSiswaState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loginGuru(username: String, password: String) {
        loginGuruUseCase(username, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _loginGuruState.value = LoginGuruState(loginData = result.data)
                }
                is Resource.Error -> {
                    _loginGuruState.value =
                        LoginGuruState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _loginGuruState.value = LoginGuruState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun verifyInput(username: String, password: String, isEitherRoleSelected: Boolean): Boolean {
        return username.isNotBlank() && password.isNotBlank() && isEitherRoleSelected
    }
}