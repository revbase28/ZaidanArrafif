package com.revbase.zaidanarrafif.data.remote.quran.dto

import com.revbase.zaidanarrafif.domain.models.SurahDetail

// For tafsir there is two kind of value for one key from the api response
// To accommodate all the possibilities of value provided by the api, i decided to make tafsir have Any Type
// tafsir Type can either be Tafsir or TafsirWithObj

data class SurahDetailDTO(
    val name: Name,
    val number: Int,
    val numberOfVerses: Int,
    val preBismillah: Any,
    val revelation: Revelation,
    val sequence: Int,
    val tafsir: Any,
    val verses: List<Verse>
)

fun SurahDetailDTO.toSurahDetail() =
    SurahDetail(
        name = name.transliteration.id,
        surahNumber = number,
        revelation = revelation.id,
        numberOfVerses = numberOfVerses,
        verses = verses.map { item ->
            item.toVerseSimplified()
        }
    )