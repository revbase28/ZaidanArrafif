package com.revbase.zaidanarrafif.data.remote.quran.dto

data class Meta(
    val hizbQuarter: Int,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val sajda: Sajda
)