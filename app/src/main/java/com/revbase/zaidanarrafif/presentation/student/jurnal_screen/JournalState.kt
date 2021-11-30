package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import com.revbase.zaidanarrafif.domain.models.Journal

data class JournalState(
    val isLoading: Boolean = false,
    val error: String = "",
    val journal: Journal? = null
        )