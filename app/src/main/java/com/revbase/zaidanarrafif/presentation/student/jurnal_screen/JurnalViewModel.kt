package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.use_case.get_journal_activities.GetJournalActivitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JurnalViewModel @Inject constructor(
    private val getJournalActivitiesUseCase: GetJournalActivitiesUseCase,
):ViewModel() {
    private val _state = mutableStateOf(JournalState())
    val state: State<JournalState> = _state

    init {
        Log.d("journal_vm","Journal VM is Executed")

    }

    private fun getAllJournalActivities()
    {

    }

}