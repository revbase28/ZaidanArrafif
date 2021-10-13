package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.use_case.get_journal_activities.GetAllDailyJournalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class JurnalViewModel @Inject constructor(
    private val getAllDailyJournalUseCase: GetAllDailyJournalUseCase,
):ViewModel() {
    private val _state = mutableStateOf(JournalState())
    val state: State<JournalState> = _state

    init {
        Log.d("journal_vm","Journal VM is Executed")
        getAllDailyJournal()

    }

    private fun getAllDailyJournal()
    {
        getAllDailyJournalUseCase().onEach { result->
            when(result){
                is Resource.Success ->{
                    _state.value = JournalState(journalList = result.data?: emptyList())
                }
                is Resource.Error->{
                    _state.value = JournalState(error = result.message?:"Failed to fetch data")
                }
                is Resource.Loading ->{
                    _state.value = JournalState(isLoading = true)
                }
            }
            Log.d("state_value","${_state.value}")
        }.launchIn(viewModelScope)
    }

}