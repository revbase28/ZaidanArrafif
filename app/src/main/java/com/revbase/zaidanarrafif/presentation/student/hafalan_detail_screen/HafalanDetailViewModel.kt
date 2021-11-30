package com.revbase.zaidanarrafif.presentation.student.hafalan_detail_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioFromLinkUseCase
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HafalanDetailViewModel @Inject constructor(
    private val audioUseCase: PlayAudioFromLinkUseCase
): ViewModel(){

    val audioProgress = mutableStateOf(0F)
    val duration = mutableStateOf(1F)
    val currentSec = mutableStateOf(1)

    fun playAudioFromUrl(url: String) {
        audioUseCase(url)
        audioUseCase.listenToMediaProgress().onEach {
            audioProgress.value = it["progress"]!!.toFloat()
            duration.value = it["duration"]!!.toFloat()
            currentSec.value = it["currentSec"]!!.toInt()
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun stopAudio() {
        audioUseCase.stop()
    }
}