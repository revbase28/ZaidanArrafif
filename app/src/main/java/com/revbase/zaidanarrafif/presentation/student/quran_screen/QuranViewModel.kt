package com.revbase.zaidanarrafif.presentation.student.quran_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.models.SurahDetail
import com.revbase.zaidanarrafif.domain.use_case.download_audio_from_url.DownloadAudioFromUrlUseCase
import com.revbase.zaidanarrafif.domain.use_case.get_all_surah.GetAllSurahUseCase
import com.revbase.zaidanarrafif.domain.use_case.get_surah_detail.GetSurahDetailUseCase
import com.revbase.zaidanarrafif.presentation.student.surah_screen.DownloadState
import com.revbase.zaidanarrafif.presentation.student.surah_screen.SurahState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(
    private val getAllSurahUseCase: GetAllSurahUseCase,
    private val downloadAudioFromUrlUseCase: DownloadAudioFromUrlUseCase,
    private val getSurahDetailUseCase: GetSurahDetailUseCase
) : ViewModel() {

    private val _state = mutableStateOf(QuranState())
    val state: State<QuranState> = _state

    private val _surahDetailState = mutableStateOf(SurahState())

    private val _downloadState = mutableStateOf(DownloadState())
    val downloadState: State<DownloadState> = _downloadState

    init {
        Log.d("viewModel", "view model executed")
        getAllSurah()
    }

    private fun getAllSurah() {
        getAllSurahUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = QuranState(listSurah = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        QuranState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _state.value = QuranState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSurahDetailThenDownloadAudio(surahNumber: Int) {
        getSurahDetailUseCase(surahNumber).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        downloadAudioFromUrl(it)
                    }
                }
                is Resource.Error -> {
                    _surahDetailState.value =
                        SurahState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _surahDetailState.value = SurahState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun downloadAudioFromUrl(surahData: SurahDetail) {
        downloadAudioFromUrlUseCase(surahData).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _downloadState.value = DownloadState(data = result.data)
                }
                is Resource.Error -> {
                    _downloadState.value = DownloadState(
                        error = result.message
                            ?: "Terjadi kesalahan tidak terduga, coba lagi beberapa saat lagi"
                    )
                }
                is Resource.Loading -> {
                    _downloadState.value = DownloadState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}