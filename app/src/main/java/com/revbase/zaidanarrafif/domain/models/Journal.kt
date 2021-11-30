package com.revbase.zaidanarrafif.domain.models

import java.util.*

data class Journal (
    val date: String,
    val studentId: Int,
    val id:Int,
    val activityPerformed:  List<Int>
)