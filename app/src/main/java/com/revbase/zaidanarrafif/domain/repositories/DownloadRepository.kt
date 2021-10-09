package com.revbase.zaidanarrafif.domain.repositories

interface DownloadRepository {
    suspend fun downloadAudioFromUrl(surahName: String, downloadUrl: String, fileName: String)
}