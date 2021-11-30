package com.revbase.zaidanarrafif.domain.use_case.play_audio_use_case

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.lang.IllegalStateException
import java.net.URL
import javax.inject.Inject

class PlayAudioFromLinkUseCase {
    private val mp = MediaPlayer()
    private var duration = 1F

    operator fun invoke(audioUrl: String) {
        try {
            mp.setDataSource(audioUrl)
        } catch (e: IllegalStateException) {
            mp.stop()
            mp.reset()
            mp.setDataSource(audioUrl)
        }

        try {
            if(!mp.isPlaying) {
                mp.prepareAsync()
            }
        } catch (e: IllegalStateException) {
            mp.stop()
            mp.reset()
            mp.setDataSource(audioUrl)
            mp.prepareAsync()
        }

        mp.setOnPreparedListener {
            it.start()
            if(it.isPlaying) {
                duration = it.duration.toFloat()
            }
        }
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

    fun isMediaPlaying(): Boolean {
        return mp.isPlaying
    }

    fun listenToMediaProgress(): Flow<Map<String, Number>> = flow {
        while (mp.currentPosition != duration.toInt()) {
            emit(
                mapOf(
                    "duration" to duration / 1000F,
                    "progress" to (mp.currentPosition.toFloat() / duration),
                    "currentSec" to mp.currentPosition
                )
            )
        }
    }
}