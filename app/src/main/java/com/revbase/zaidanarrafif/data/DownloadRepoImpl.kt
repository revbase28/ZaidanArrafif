package com.revbase.zaidanarrafif.data

import android.content.Context
import com.revbase.zaidanarrafif.domain.repositories.DownloadRepository
import java.io.*
import java.net.URL
import javax.inject.Inject
import kotlin.jvm.Throws

class DownloadRepoImpl @Inject constructor(
    private val context: Context
): DownloadRepository {

    @Throws(IOException::class)
    override suspend fun downloadAudioFromUrl(
        surahName: String,
        downloadUrl: String,
        fileName: String
    ) {
        val url = URL(downloadUrl)
        val downloadDir = File("${context.filesDir}/$surahName")
        if(!downloadDir.exists()) {
            downloadDir.mkdirs()
        }
        val downloadFile = File(downloadDir, "$fileName.mp3")
        val outputStream = FileOutputStream(downloadFile)
        var inputStream: BufferedInputStream? = null
        try {
            val connection = url.openConnection()
            connection.connect()
            inputStream = BufferedInputStream(url.openStream())
            val data = ByteArray(1024)
            var count = inputStream.read(data)
            var total = count
            while (count != -1) {
                outputStream.write(data, 0, count)
                count = inputStream.read(data)
                total += count
            }
        } catch (e: IOException) {
            throw IOException("Unduh surah gagal karena tidak ada koneksi, coba periksa sambungan internet")
        } finally {
            outputStream.flush()
            outputStream.close()
            inputStream?.close()
        }
    }
}