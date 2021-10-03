package com.revbase.zaidanarrafif.data.remote.dto

import com.revbase.zaidanarrafif.domain.models.VerseSimplified

// For tafsir there is two kind of value for one key from the api response
// To accommodate all the possibilities of value provided by the api, i decided to make tafsir have Any Type
// tafsir Type can either be Tafsir or TafsirWithObj

data class Verse(
    val audio: Audio,
    val meta: Meta,
    val number: Number,
    val tafsir: Any,
    val text: Text,
    val translation: Translation
)

fun Verse.toVerseSimplified() =
    VerseSimplified(
        text = text.arab,
        audio = audio,
        verseNumber = number.inSurah,
        translation = translation.id
    )