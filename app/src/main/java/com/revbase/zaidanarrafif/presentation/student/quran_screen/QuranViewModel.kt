package com.revbase.zaidanarrafif.presentation.student.quran_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.get_all_surah.GetAllSurahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val getAllSurahUseCase: GetAllSurahUseCase
): ViewModel() {

    private val _state = mutableStateOf(QuranState())
    val state: State<QuranState> = _state

    init {
        Log.d("viewModel", "view model executed")
        getAllSurah()
    }

    private fun getAllSurah() {
        getAllSurahUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = QuranState(listSurah = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = QuranState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _state.value = QuranState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}