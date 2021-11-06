package com.revbase.zaidanarrafif.data.remote.zaidan.dto

data class HafalanDTO(
    val catatan: String,
    val date: String,
    val guru: Guru,
    val id_hafalan: Int,
    val komentar: String?,
    val link_rekaman: String,
    val siswa: Siswa,
    val star: Int,
    val surat: String
)