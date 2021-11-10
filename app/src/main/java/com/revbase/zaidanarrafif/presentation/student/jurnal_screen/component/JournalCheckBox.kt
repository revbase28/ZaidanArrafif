package com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.revbase.zaidanarrafif.domain.models.Journal
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.JournalViewModel
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun JournalCheckBox(
    id: Int,
    viewModel: JournalViewModel = hiltViewModel(),
    journalPerformed: Journal?
)
{
    val isChecked = remember { mutableStateOf(false) }


    val activityPerformed = journalPerformed?.activityPerformed
    val isInActivity = activityPerformed?.any {
        it==id
    }

    if(isInActivity == true){
        isChecked.value = true
    }
    Checkbox(
        checked = isChecked.value,
        onCheckedChange = {

            isChecked.value = it
            viewModel.createDailyJournal(listOf(id))
                          },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Blue,
            uncheckedColor = Color.DarkGray,
            checkmarkColor = Color.White
        )
    )
}