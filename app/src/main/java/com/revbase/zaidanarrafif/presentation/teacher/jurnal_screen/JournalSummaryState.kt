package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen

import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.models.JournalSummary

data class JournalSummaryState(
    val isLoading: Boolean = false,
    val journalSummaries: List<JournalSummary> = emptyList(),
    val error: String = ""
)
