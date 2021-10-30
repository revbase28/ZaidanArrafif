package com.revbase.zaidanarrafif.presentation.student.main_student

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentMainViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
): ViewModel() {

    private val _state = mutableStateOf(Siswa())
    val studentData = _state

    init {
        viewModelScope.launch {
            preferenceManager.getStudentDataFromPreferences().collect {
                _state.value = it
            }
        }
    }
}