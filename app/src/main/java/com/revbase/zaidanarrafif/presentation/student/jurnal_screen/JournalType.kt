package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

sealed class JournalType(val queryString : String?) {
    object Ibadah : JournalType("ibadah")
    object Kegiatan : JournalType("kegiatan")
    object Today : JournalType(null)
}