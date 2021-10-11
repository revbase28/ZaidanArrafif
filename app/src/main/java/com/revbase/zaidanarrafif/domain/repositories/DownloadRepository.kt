package com.revbase.zaidanarrafif.domain.repositories

import kotlinx.coroutines.Job

interface DownloadRepository {
    suspend fun downloadAudioFromUrl(surahName: String, downloadUrl: String, fileName: String)
}