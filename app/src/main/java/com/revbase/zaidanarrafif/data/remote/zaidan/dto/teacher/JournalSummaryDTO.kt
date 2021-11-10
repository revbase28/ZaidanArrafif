package com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher

import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.models.JournalSummary
import com.revbase.zaidanarrafif.domain.models.StudentActivity

data class JournalSummaryDTO(
    val id: Int,
    val deskripsi: String,
    val jenis: String,
    val url_icon : String,
    val jumlah_siswa_melakukan: Int
)

fun JournalSummaryDTO.toJournalSummary(): JournalSummary {
    return JournalSummary(
        activity = StudentActivity(id, deskripsi, jenis, url_icon),
        totalStudent = jumlah_siswa_melakukan
    )
}
