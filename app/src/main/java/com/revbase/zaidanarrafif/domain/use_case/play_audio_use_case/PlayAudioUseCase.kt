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

class PlayAudioUseCase @Inject constructor(
    private val context: Context
) {

    private var mp: MediaPlayer? = null
    private var isMpReleased = false

    operator fun invoke(fileName: String, surah: String): MediaPlayer {
        val dir = File("${context.filesDir}/$surah/$fileName")
        return play(dir)
    }

    operator fun invoke(file: File): MediaPlayer {
        return play(file)
    }

    private fun play(file: File): MediaPlayer {
        val uri = Uri.fromFile(file)
        if (isMpReleased || mp == null) {
            mp = MediaPlayer.create(context, uri)
            isMpReleased = false
        }
        mp?.let { mp ->
            if(!isMpReleased){
                mp.stop()
            }
            mp.reset()
            mp.setDataSource(context, uri)
            mp.prepare()
            mp.start()
        }
        return mp!!
    }

    fun pause() {
        mp!!.pause()
    }

    fun playAgain(): MediaPlayer {
        mp?.let { mp ->
            mp.seekTo(mp.currentPosition)
            mp.start()
        }
        return mp!!
    }

    fun stop() {
        mp?.let { mp ->
            if(!isMpReleased){
                mp.stop()
                mp.release()
                isMpReleased = true
            }
        }
    }

    fun isMediaPlaying(): Boolean {
        return mp!!.isPlaying
    }
}