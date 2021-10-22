package com.revbase.zaidanarrafif.domain.repositories

import kotlinx.coroutines.Job
import java.io.File

interface DownloadRepository {

    suspend fun downloadAudioFromUrl(surahName: String, numberOfVerses: Int, downloadUrl: String, fileName: String)

    fun checkIfFolderExist(folder: String): Boolean

    fun checkIfFolderExist(folder: String, numberOfFile: Int): Boolean

    suspend fun rollbackDownload(folder: String)
}