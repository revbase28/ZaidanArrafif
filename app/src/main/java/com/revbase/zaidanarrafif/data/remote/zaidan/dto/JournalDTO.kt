package com.revbase.zaidanarrafif.data.remote.zaidan.dto

import com.revbase.zaidanarrafif.domain.models.Journal

data class JournalDTO (
    val date:String,
    val id_siswa:Int,
    val id: Int,
    val kegiatan_dilakukan:List<Int>,
)

fun JournalDTO.toJournal():Journal{
    return Journal(
        id = this.id,
        date = this.date,
        studentId = this.id_siswa,
        activityPerformed = this.kegiatan_dilakukan
    )
}