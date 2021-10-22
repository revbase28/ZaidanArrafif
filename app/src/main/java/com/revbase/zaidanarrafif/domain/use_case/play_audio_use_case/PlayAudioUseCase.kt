package com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import java.io.File
import java.lang.IllegalStateException
import javax.inject.Inject

class PlayAudioUseCase @Inject constructor(
    private val context: Context
) {
    private val mp = MediaPlayer()
    operator fun invoke(fileName: String, surah: String): MediaPlayer {
        val dir = File("${context.filesDir}/$surah/$fileName")
        val uri = Uri.fromFile(dir)
        try {
            mp.setDataSource(context, uri)
        } catch (e: IllegalStateException) {
            mp.stop()
            mp.reset()
            mp.setDataSource(context, uri)
        }
        mp.prepare()
        mp.start()
        return mp
    }

    fun pause() {
        mp.pause()
    }

    fun playAgain(): MediaPlayer {
        mp.seekTo(mp.currentPosition)
        mp.start()
        return mp
    }

    fun stop() {
        mp.stop()
    }

    fun isMediaPlaying(): Boolean{
        return mp.isPlaying
    }
}