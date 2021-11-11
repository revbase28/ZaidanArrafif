package com.revbase.zaidanarrafif.presentation.student.hafalan_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.HafalanDTO
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.use_case.get_hafalan_use_case.GetHafalanUseCase
import com.revbase.zaidanarrafif.presentation.student.quran_screen.QuranState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HafalanViewModel @Inject constructor(
    private val getHafalanUseCase: GetHafalanUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel() {

    private val _token = mutableStateOf("")
    private val _studentData = mutableStateOf(Siswa())
    private val _getHafalanState = mutableStateOf(HafalanState())
    val getHafalanState: State<HafalanState> = _getHafalanState

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token.value = it
            }
        }
    }

    fun getHafalanList() {
        getStudentData()
        getHafalanUseCase(_studentData.value.nis, _token.value).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getHafalanState.value = HafalanState(listHafalan = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _getHafalanState.value =
                        HafalanState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _getHafalanState.value = HafalanState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStudentData() {
        viewModelScope.launch {
            launch {
                preferenceManager.getStudentDataFromPreferences().collect {
                    _studentData.value = it
                }
            }.join()
        }
    }

}