package com.revbase.zaidanarrafif.data.remote.zaidan.dto.teacher

import com.revbase.zaidanarrafif.data.remote.zaidan.dto.Siswa
import com.revbase.zaidanarrafif.data.remote.zaidan.dto.StudentActivityDTO

data class TeacherActivitySummary(
    val kegiatan: StudentActivityDTO?,
    val siswa: List<Siswa> = emptyList()
)
