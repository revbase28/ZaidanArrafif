package com.revbase.zaidanarrafif.domain.use_case.record_audio_use_case

import android.content.Context
import android.media.MediaRecorder
import java.io.File

class RecordAudioUseCase(
    private val context: Context
) {

    private var mediaRecoder: MediaRecorder? = null
    private var outputDir: File? = null
    private var audioTitle: String = ""

    operator fun invoke(audioTitle: String) {
        outputDir = File("${context.filesDir}/${audioTitle}.mp3")
        this.audioTitle = audioTitle
        mediaRecoder = MediaRecorder()
        mediaRecoder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecoder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecoder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecoder?.setOutputFile(outputDir)
        startRecording()
    }

    private fun startRecording() {
        mediaRecoder?.prepare()
        mediaRecoder?.start()
    }

    fun stopRecording(): Map<String, File?> {
        mediaRecoder?.stop()
        return mapOf(
            audioTitle to outputDir
        )
    }

    fun deleteRecording(file: File?) {
        file?.let {
            it.delete()
        }
    }
}