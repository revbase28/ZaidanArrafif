package com.revbase.zaidanarrafif.presentation.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.Constant.AS_STUDENT
import com.revbase.zaidanarrafif.common.Constant.AS_TEACHER
import com.revbase.zaidanarrafif.common.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _token = mutableStateOf("")
    private val _userType = mutableStateOf("")

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token.value = it
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return _token.value.isNotBlank()
    }

    fun isStudent(): Boolean {
        viewModelScope.launch {
            launch {
                preferenceManager.getUserType().collect {
                    _userType.value = it
                }
            }.join()
        }
        return _userType.value == AS_STUDENT
    }
}