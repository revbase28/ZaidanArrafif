package com.revbase.zaidanarrafif.domain.use_case.get_journal_activities

import com.revbase.zaidanarrafif.domain.repositories.JournalRepository
import javax.inject.Inject

class GetJournalActivitiesUseCase @Inject constructor(
    private val repository: JournalRepository
) {


}