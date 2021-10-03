package com.revbase.zaidanarrafif.data.remote.dto

import com.revbase.zaidanarrafif.domain.models.Surah

data class SurahDTO(
    val name: Name,
    val number: Int,
    val numberOfVerses: Int,
    val revelation: Revelation,
    val sequence: Int,
    val tafsir: Tafsir
)

fun SurahDTO.toSurah(): Surah {
    return Surah(
        name = name.transliteration.id,
        surahNumber = number,
        revelation = revelation.id,
        numberOfVerses = numberOfVerses
    )
}