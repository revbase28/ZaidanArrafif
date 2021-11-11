package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.get_hafalan_use_case.GetHafalanBasedOnGuruUseCase
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.HafalanState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherHafalanViewModel @Inject constructor(
    private val getHafalanBasedOnGuruUseCase: GetHafalanBasedOnGuruUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel() {
    private var _token = ""
    private var _nip = 0

    private val _hafalanState = mutableStateOf(HafalanState())
    var hafalanState = _hafalanState

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token = it
            }
        }

        viewModelScope.launch {
            preferenceManager.getNip().collect {
                _nip = it
            }
        }
    }

    fun getHafalan() {
        getHafalanBasedOnGuruUseCase(_nip, _token).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _hafalanState.value = HafalanState(listHafalan = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _hafalanState.value = HafalanState(isLoading = true)
                }
                is Resource.Error -> {
                    _hafalanState.value = HafalanState(error = result.message ?: "Failed to fetch data")
                }
            }
        }.launchIn(viewModelScope)
    }
}