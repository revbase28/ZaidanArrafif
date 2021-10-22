package com.revbase.zaidanarrafif.domain.use_case.check_if_folder_exist

import com.revbase.zaidanarrafif.domain.repositories.DownloadRepository
import javax.inject.Inject

class CheckIfFolderExistUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) {
    operator fun invoke(folderName: String): Boolean {
        return downloadRepository.checkIfFolderExist(folderName)
    }

    operator fun invoke(folderName: String, numberOfFiles: Int): Boolean {
        return downloadRepository.checkIfFolderExist(folderName, numberOfFiles)
    }
}