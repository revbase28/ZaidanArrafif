package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import com.revbase.zaidanarrafif.domain.models.StudentActivity

data class StudentActivityDTO(
    val id: Int,
    val deskripsi: String,
    val jenis: String,
    val url_icon : String,
)

fun StudentActivityDTO.toStudentActivity():StudentActivity{

    return  StudentActivity(
        id = this.id,
        description = deskripsi,
        type = jenis,
        iconURL = url_icon
    )

}