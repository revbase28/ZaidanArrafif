package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Siswa(
    val guru_setoran: String = "",
    val kelas: String = "",
    val nama_panggilan: String = "",
    val nama_siswa: String = "",
    val nis: Int = 0,
    val stars: Int = -1,
    val tahun_masuk: Int = 0,
    val tanggal_lahir: String = "",
    val username: String = "",
    val walikelas: String = ""
) : Parcelable