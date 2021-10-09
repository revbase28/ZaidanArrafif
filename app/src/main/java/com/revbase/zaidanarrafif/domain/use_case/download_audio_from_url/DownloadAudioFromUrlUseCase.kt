package com.revbase.zaidanarrafif.domain.use_case.download_audio_from_url

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
    operator fun invoke(surahData: SurahDetail) {
        var isDownloadError = false
        CoroutineScope(Dispatchers.IO).launch {
            for (verse in surahData.verses) {
                val fileName = "${surahData.name}_${verse.verseNumber}"
                launch {
                    try {
                        repository.downloadAudioFromUrl(
                            surahName = surahData.name,
                            downloadUrl = verse.audio.primary,
                            fileName = fileName
                        )
                    } catch (e: IOException) {
                        print("Canceled\n")
                        isDownloadError = true
                    }
                }.join()
                if (isDownloadError) break
            }
        }
    }
}