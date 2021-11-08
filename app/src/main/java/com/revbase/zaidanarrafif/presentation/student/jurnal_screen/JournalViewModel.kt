package com.revbase.zaidanarrafif.presentation.student.jurnal_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revbase.zaidanarrafif.common.PreferenceManager
import com.revbase.zaidanarrafif.common.Resource
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.domain.use_case.create_daily_journal.CreateDailyJournalUseCase
import com.revbase.zaidanarrafif.domain.use_case.get_daily_journal.GetAllDailyActivityJournalUseCase
import com.revbase.zaidanarrafif.domain.use_case.get_daily_journal.GetAllDailyWorshipJournalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class JournalViewModel @Inject constructor(
    private val getAllDailyActivityJournalUseCase: GetAllDailyActivityJournalUseCase,
    private val preferenceManager: PreferenceManager,
    private val createDailyJournalUseCase: CreateDailyJournalUseCase,
    private val getAllDailyWorshipJournalUseCase: GetAllDailyWorshipJournalUseCase,
) : ViewModel() {


    private val _state = mutableStateOf(StudentActivityState())
    val state: State<StudentActivityState> = _state

    private val _journalState = mutableStateOf(JournalState())


    val journalState : State<JournalState> = _journalState;

    private var _token = ""
    private  var _nis = 0

    init {
        Log.d("journal_vm", "Journal VM is Executed")
        viewModelScope.launch {

            preferenceManager.getToken().collect {
                println("Token")
                _token = it

            }
        }
        viewModelScope.launch {

            preferenceManager.getStudentDataFromPreferences().collect{
                println("Masuk Siswa!")
                println("=============>${it.nis}")
                _nis = it.nis
            }

            }


    }

     fun getAllDailyActivityJournal() {
        getAllDailyActivityJournalUseCase("Bearer $_token").onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = StudentActivityState(isLoading = true)
                }
                is Resource.Error -> {
                    Log.d("teserror","${result.message}")
                    _state.value = StudentActivityState(error = result.message ?: "Failed to fetch data")
                }
                is Resource.Success -> {
                    _state.value = StudentActivityState(studentActivityList = result.data ?: emptyList())
                }
            }
            Log.d("state_value", "${_state.value}")
        }.launchIn(viewModelScope)
    }
     fun getAllDailyWorshipJournal() {
        getAllDailyWorshipJournalUseCase("Bearer $_token").onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StudentActivityState(studentActivityList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    Log.d("teserror","${result.message}")

                    _state.value = StudentActivityState(error = result.message ?: "Failed to fetch data")
                }
                is Resource.Loading -> {
                    _state.value = StudentActivityState(isLoading = true)
                }
            }
            Log.d("state_value", "${_state.value}")
        }.launchIn(viewModelScope)
    }

    fun createDailyJournal(kegiatan: List<Int>){
        createDailyJournalUseCase("Bearer $_token",kegiatan,_nis ).onEach { result->
            when(result){
                is Resource.Success->{
                    _journalState.value = JournalState(journal = result.data)
                }
                is Resource.Error->{
                    _journalState.value = JournalState(error = result.message?:"Terjadi kesalahan tidak terduga")
                }
                is Resource.Loading->{
                    _journalState.value = JournalState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}