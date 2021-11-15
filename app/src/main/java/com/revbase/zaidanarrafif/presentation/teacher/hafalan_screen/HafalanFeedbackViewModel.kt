package com.revbase.zaidanarrafif.presentation.teacher.hafalan_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioFromLinkUseCase
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioUseCase
import com.revbase.zaidanarrafif.domain.use_case.update_hafalan_use_case.UpdateHafalanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HafalanFeedbackViewModel @Inject constructor(
    private val playAudioUseCase: PlayAudioFromLinkUseCase,
    private val updateHafalanUseCase: UpdateHafalanUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel() {
    private var _token = ""

    private val _state = mutableStateOf(HafalanFeedbackState())
    val state: State<HafalanFeedbackState> = _state

    val audioProgress = mutableStateOf(0F)
    val duration = mutableStateOf(1F)
    val currentSec = mutableStateOf(1)

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token = it
            }
        }
    }

    fun playAudioFromUrl(url: String) {
        playAudioUseCase(url)
        playAudioUseCase.listenToMediaProgress().onEach {
            audioProgress.value = it["progress"]!!.toFloat()
            duration.value = it["duration"]!!.toFloat()
            currentSec.value = it["currentSec"]!!.toInt()
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun updateHafalan(hafalanId: Int, comment: String, star: Int = 0) {
        updateHafalanUseCase(hafalanId, _token, comment, star).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HafalanFeedbackState(hafalan = result.data)
                }
                is Resource.Loading -> {
                    _state.value = HafalanFeedbackState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HafalanFeedbackState(error = result.message ?: "Failed to fetch data")
                }
            }
        }.launchIn(viewModelScope)
    }
}