package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.use_case.get_journal_summary.GetJournalSummaryUseCase
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.JournalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherJournalViewModel @Inject constructor(
    private val getJournalSummaryUseCase: GetJournalSummaryUseCase,
    private val preferenceManager: PreferenceManager
): ViewModel() {
    private var _token = ""
    private var _nip = 0
    private val _state = mutableStateOf(JournalSummaryState())
    val state: State<JournalSummaryState> = _state

    init {
        viewModelScope.launch {
            preferenceManager.getToken().collect {
                _token = it
                Log.d("SummaryVm", it)
            }
        }

        viewModelScope.launch {
            preferenceManager.getNip().collect {
                _nip = it
                Log.d("SummaryVM", it.toString())
            }
        }
    }

    fun getJournalSummary(jenis: String?) {
        getJournalSummaryUseCase(_nip, "Bearer $_token", jenis).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = JournalSummaryState(journalSummaries = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = JournalSummaryState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = JournalSummaryState(error = result.message ?: "Failed to fetch data")
                }
            }
        }.launchIn(viewModelScope)
    }

}