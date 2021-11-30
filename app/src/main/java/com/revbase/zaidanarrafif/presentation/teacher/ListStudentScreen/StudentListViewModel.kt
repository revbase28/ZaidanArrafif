package com.revbase.zaidanarrafif.presentation.teacher.ListStudentScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.get_student.GetStudentBasedOnTeacherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentBasedOnTeacherUseCase: GetStudentBasedOnTeacherUseCase,
    private val preferenceManager: PreferenceManager,
): ViewModel() {
    private var _token = ""
    private var _nip = 0

    private val _state = mutableStateOf(StudentState())
    val state: State<StudentState> = _state

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

    fun getStudent() {
        getStudentBasedOnTeacherUseCase(_nip, _token).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StudentState(studentList = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = StudentState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = StudentState(error = result.message ?: "Failed to fetch data")
                }
            }
        }.launchIn(viewModelScope)
    }
}