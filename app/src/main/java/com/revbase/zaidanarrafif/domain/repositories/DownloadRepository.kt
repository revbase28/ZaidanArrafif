package com.revbase.zaidanarrafif.domain.repositories

import kotlinx.coroutines.Job
import java.io.File

interface DownloadRepository {
    suspend fun downloadAudioFromUrl(surahName: String, downloadUrl: String, fileName: String)

    fun checkIfFolderExist(folder: String): Boolean

    suspend fun rollbackDownload(folder: String)
}