package com.revbase.zaidanarrafif.presentation.student.surah_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.models.SurahDetail
import com.revbase.zaidanarrafif.domain.models.VerseSimplified
//import com.revbase.zaidanarrafif.domain.use_case.download_audio_from_url.DownloadAudioFromUrlUseCase
import com.revbase.zaidanarrafif.domain.use_case.get_surah_detail.GetSurahDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(
    private val getSurahDetailUseCase: GetSurahDetailUseCase,
    val state: State<SurahState> = _state
    private val _downloadState = mutableStateOf(DownloadState())

    fun getSurahDetail(surahNumber: Int) {
        getSurahDetailUseCase(surahNumber).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SurahState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value =
                        SurahState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading -> {
                    _state.value = SurahState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun downloadAudioFromUrl(surahData: SurahDetail) {
        downloadAudioFromUrlUseCase(surahData).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _downloadState.value = DownloadState(data = result.data)
                }
                is Resource.Error -> {
                    _downloadState.value = DownloadState(
                        error = result.message
                            ?: "Terjadi kesalahan tidak terduga, cobalah beberapa saat lagi"
                    )
                }
                is Resource.Loading -> {
                    _downloadState.value = DownloadState(isLoading = true)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}