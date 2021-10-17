package com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import java.io.File
import javax.inject.Inject

class PlayAudioUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(fileName: String, surah: String) {
        val mp = MediaPlayer()
        val dir = File("${context.filesDir}/$surah/$fileName")
        val uri = Uri.fromFile(dir)
        mp.setDataSource(context, uri)
        mp.prepare()
        mp.start()
    }
}