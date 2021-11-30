package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Guru(
    val nama_guru: String = "",
    val nip: Int = 0,
    val username: String = ""
) : Parcelable