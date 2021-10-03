package com.revbase.zaidanarrafif.domain.models

import com.revbase.zaidanarrafif.data.remote.dto.Audio

data class VerseSimplified(
    val text : String,
    val audio: Audio,
    val verseNumber: Int,
    val translation: String
)