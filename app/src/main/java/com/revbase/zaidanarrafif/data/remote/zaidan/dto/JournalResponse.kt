package com.revbase.zaidanarrafif.data.remote.zaidan.dto


    data class JournalResponse (
        val success: Boolean,
        val message: String,
        val data: List<DailyJournalDTO>
    )
