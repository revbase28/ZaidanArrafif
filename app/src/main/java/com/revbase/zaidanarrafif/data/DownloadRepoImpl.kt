package com.revbase.zaidanarrafif.data

import android.content.Context
import com.revbase.zaidanarrafif.domain.repositories.DownloadRepository
import java.io.BufferedInputStream
import java.net.URL
import javax.inject.Inject

class DownloadRepoImpl @Inject constructor(
    private val context: Context
): DownloadRepository {
    override suspend fun downloadAudioFromUrl(
        surahName: String,
        downloadUrl: String,
        fileName: String
    ) {
        val url = URL(downloadUrl)
        val filename = "${fileName}.mp3"
        val outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
        val connection = url.openConnection()
        connection.connect()
        val inputStream = BufferedInputStream(url.openStream())
        val data = ByteArray(1024)
        var count = inputStream.read(data)
        var total = count
        while (count != -1) {
            outputStream.write(data, 0, count)
            count = inputStream.read(data)
            total += count
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }
}