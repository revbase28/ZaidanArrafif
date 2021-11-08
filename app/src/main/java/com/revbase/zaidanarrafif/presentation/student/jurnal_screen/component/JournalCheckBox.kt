package com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.revbase.zaidanarrafif.presentation.student.jurnal_screen.JournalViewModel
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun JournalCheckBox(
    id : Int,
    viewModel: JournalViewModel = hiltViewModel()
)
{
    val isChecked = remember { mutableStateOf(false) }
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