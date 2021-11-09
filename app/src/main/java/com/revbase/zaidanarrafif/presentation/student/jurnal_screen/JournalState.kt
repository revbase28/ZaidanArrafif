package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import com.revbase.zaidanarrafif.domain.models.Journal

<<<<<<< Updated upstream
data class JournalState (
    val  isLoading: Boolean = false,
    val  journalList: List<Journal> = emptyList(),
    val error: String = ""

)
=======


data class JournalState(
    val isLoading: Boolean = false,
    val error: String = "",
    val journal: Journal? = null
        )
>>>>>>> Stashed changes
