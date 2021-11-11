package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HafalanDTO(
    val catatan: String?,
    val date: String,
    val guru: Guru,
    val id_hafalan: Int,
    val komentar: String?,
    val link_rekaman: String,
    val siswa: Siswa,
    val star: Int,
    val surat: String
) : Parcelable