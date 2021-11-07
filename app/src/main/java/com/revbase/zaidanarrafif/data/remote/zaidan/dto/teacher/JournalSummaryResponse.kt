package com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher

data class JournalSummaryResponse(
    val success: Boolean,
    val message: String,
    val data: List<JournalSummaryDTO>
)
