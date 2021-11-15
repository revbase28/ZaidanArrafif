package com.revbase.zaidanarrafif.presentation.student.tambah_hafalan

import android.util.Log
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.PostHafalanResponse
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioFromLinkUseCase
import com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case.PlayAudioUseCase
import com.revbase.zaidanarrafif.domain.use_case.post_hafalan_use_case.PostHafalanUseCase
import com.revbase.zaidanarrafif.domain.use_case.record_audio_use_case.RecordAudioUseCase
import com.revbase.zaidanarrafif.presentation.student.hafalan_screen.HafalanState
import com.revbase.zaidanarrafif.presentation.student.surah_screen.SurahState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class TambahHafalanViewModel @Inject constructor(
    private val recordAudioUseCase: RecordAudioUseCase,
    private val playAudioUseCase: PlayAudioUseCase,
    private val postHafalanUseCase: PostHafalanUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel() {

    private val _surahReferenceList = Constant.listSurah.toList()
    private val _isRecording = mutableStateOf(false)
    private val _token = mutableStateOf("")
    private val _postHafalanState = mutableStateOf( PostHafalanState() )
    val postHafalanState: State<PostHafalanState> = _postHafalanState

    val recordingFile = mutableStateOf<File?>(null)
    val recordingFileTitle = mutableStateOf("")

    val surahList = mutableStateOf<List<String>>(_surahReferenceList)
    val studentData = mutableStateOf(Siswa())

    init {
        viewModelScope.launch {
            preferenceManager.getStudentDataFromPreferences().collect {
                studentData.value = it
            }
        }
    }

    fun recordAudio(audioFileName: String) {
        recordAudioUseCase(audioFileName)
        _isRecording.value = true
    }

    fun stopAudioRecording(): String{
        recordAudioUseCase.stopRecording().forEach {
            recordingFile.value = it.value
            recordingFileTitle.value = it.key
        }

        _isRecording.value = false
        return recordingFileTitle.value
    }

    fun playAudio(onFinishPlaying: () -> Unit) {
        recordingFile.value?.let {
            playAudioUseCase(it).setOnCompletionListener {
                onFinishPlaying()
            }
        }
    }

    fun deleteRecording() {
        recordAudioUseCase.deleteRecording(recordingFile.value)
    }

    fun postHafalan(surahName: String, catatan: String) {
        getToken()
        recordingFile.value?.let {
            postHafalanUseCase(
                token = "Bearer ${_token.value}",
                nis = studentData.value.nis,
                surahName = surahName,
                note = catatan,
                recording = it
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _postHafalanState.value = PostHafalanState(isPostingSuccess = result.data ?: false)
                    }
                    is Resource.Error -> {
                        _postHafalanState.value =
                            PostHafalanState(error = result.message ?: "Terjadi kesalahan tidak terduga")
                    }
                    is Resource.Loading -> {
                        _postHafalanState.value = PostHafalanState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun filterSurah(surahName: String) {
        viewModelScope.launch {
            if(surahName.isEmpty()) {
                surahList.value = _surahReferenceList
            }
            val searchResult = _surahReferenceList.filter {
                it.contains(surahName.trim(), ignoreCase = true)
            }
            surahList.value = searchResult
            surahList.value.forEach {
                Log.d("Surah List", it)
            }
        }
    }

    fun stopAudio() {
        playAudioUseCase.stop()
    }

    private fun getToken() {
        viewModelScope.launch {
            launch {
                preferenceManager.getToken().collect {
                    _token.value = it
                }
            }.join()
        }
    }
}