package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import com.revbase.zaidanarrafif.domain.models.Journal

data class DailyActivityDTO(
    val id: Int,
    val deskripsi: String,
    val jenis: String,
    val url_icon : String,
)

fun DailyActivityDTO.toJournal():Journal{
    return  Journal(
        id = this.id,
        description = deskripsi,
        type = jenis,
        iconURL = url_icon
    )

}