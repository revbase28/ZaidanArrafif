package com.revbase.zaidanarrafif.domain.use_case.download_audio_from_url

import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.common.Constant.DOWNLOAD_DONE
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.models.Surah
import com.revbase.zaidanarrafif.domain.models.SurahDetail
import com.revbase.zaidanarrafif.domain.repositories.DownloadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws

class DownloadAudioFromUrlUseCase @Inject constructor(
    private val repository: DownloadRepository
) {
    operator fun invoke(surahData: SurahDetail): Flow<Resource<Int>> = flow {
        try{
            emit(Resource.Loading<Int>())
            var isDownloadError = false
            for (verse in surahData.verses) {
                val fileName = "${surahData.name}_${verse.verseNumber}"
                try {
                    repository.downloadAudioFromUrl(
                        surahName = surahData.name,
                        downloadUrl = verse.audio.primary,
                        fileName = fileName
                    )
                } catch (e: IOException) {
                    emit(Resource.Error<Int>(e.message ?: "Terjadi kesalahan saat mengunduh, coba beberapa saat lagi"))
                    isDownloadError = true
                    print("download Canceled\n")
                    break
                }
                print("$fileName downloaded\n")
                emit(Resource.Success<Int>(data = verse.verseNumber))
            }
            if(!isDownloadError) {
                print("download done")
                emit(Resource.Success<Int>(DOWNLOAD_DONE))
            } else {
                print("Canceled")
                repository.rollbackDownload(surahData.name)
            }
        } catch (e: HttpException) {
            emit(Resource.Error<Int>(e.localizedMessage ?: "Terjadi kesalahan saat mengunduh, coba beberapa saat lagi"))
        } catch (e: IOException) {
            emit(Resource.Error<Int>("Unduh surah gagal karena tidak ada koneksi, coba periksa sambungan internet"))
        }
    }
}