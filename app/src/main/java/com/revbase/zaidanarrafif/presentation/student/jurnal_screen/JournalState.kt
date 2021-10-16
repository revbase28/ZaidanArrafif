package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import com.revbase.zaidanarrafif.domain.models.Journal

data class JournalState (
    val  isLoading: Boolean = false,
    val  journalList: List<Journal> = emptyList(),
    val error: String = ""

)