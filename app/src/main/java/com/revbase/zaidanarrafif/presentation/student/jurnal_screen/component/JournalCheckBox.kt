package com.revbase.zaidanarrafif.presentation.student.jurnal_screen.component

import android.util.Log
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.revbase.zaidanarrafif.presentation.ui.theme.Blue
<<<<<<< Updated upstream
=======
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.stream.IntStream
>>>>>>> Stashed changes

@Composable
fun JournalCheckBox()
{
    val isChecked = remember { mutableStateOf(false) }
<<<<<<< Updated upstream
    Checkbox(
        checked = isChecked.value,
        onCheckedChange = { isChecked.value = it },
=======
    val activityPerformed = journalPerformed?.activityPerformed

    //check apakah kegiatan sudah dilakukan
    if (activityPerformed != null) {
        for (n in activityPerformed) {
            if (n == id) {
                isChecked.value = true
                break
            }
        }
    }

    Checkbox(
        checked = isChecked.value,
        onCheckedChange = {
            Log.d("It","$it")

            isChecked.value = !isChecked.value
            viewModel.createDailyJournal(listOf(id))
                          },
>>>>>>> Stashed changes
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Blue,
            uncheckedColor = Color.DarkGray,
            checkmarkColor = Color.White
        )
    )
}