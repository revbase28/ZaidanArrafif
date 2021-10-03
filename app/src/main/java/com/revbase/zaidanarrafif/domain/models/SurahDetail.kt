package com.revbase.zaidanarrafif.domain.models

data class SurahDetail(
    val name: String,
    val surahNumber: Int,
    val revelation : String,
    val numberOfVerses: Int,
    val verses: List<VerseSimplified>
)