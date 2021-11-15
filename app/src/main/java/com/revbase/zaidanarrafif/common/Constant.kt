package com.revbase.zaidanarrafif.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.revbase.zaidanarrafif.R

object Constant {
    const val QURAN_API_BASE_URL = "https://api.quran.sutanlab.id/"
    const val LOGOUT_SUCCESS = 204
    const val AS_STUDENT = "student"
    const val AS_TEACHER = "teacher"
    const val AUDIO_PLAYING_STATE = "isPlayingAudio"
    const val CURRENT_PLAYED_SURAH = "surahPlayed"
    const val CURRENT_PLAYED_AYAH = "ayahPlayed"
    const val DOWNLOAD_DONE = 1024
    const val ALFATIHAH = 1
    const val ATTAUBAH = 9
    const val PARAM_SURAH_NUMBER = "surah number"
    val LATO_FONT_FAMILY = FontFamily(
        Font(R.font.lato_regular, FontWeight.Normal),
        Font(R.font.lato_bold, FontWeight.Bold),
    )

    const val PARAM_ACTIVITY_ID = "activity_id"
    var listSurah = mutableListOf<String>()
}