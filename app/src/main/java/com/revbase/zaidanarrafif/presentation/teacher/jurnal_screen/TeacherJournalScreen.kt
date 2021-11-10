package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen

import android.graphics.fonts.FontStyle
import android.text.format.DateFormat.format
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.flowlayout.FlowColumn
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.Screen
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component.DatePicker
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component.JournalSummaryItem
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component.JournalTypeDropdown
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoilApi
@Composable
fun TeacherJournalScreen(
    navController: NavController,
    viewModel: TeacherJournalViewModel = hiltViewModel()
) {
    var date by remember {
        mutableStateOf(SimpleDateFormat("dd-MM-yyyy", Locale.US).format(Date()))
    }
    var typeOfJournal by remember { mutableStateOf("Kegiatan") }

    LaunchedEffect(key1 = Unit) {
        viewModel.getJournalSummary(typeOfJournal)
    }
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Riwayat Jurnal Kegiatan $date",
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(18.dp))
        DatePicker(
            currentDate = date,
            onDateClick = {
                date = it
                viewModel.getJournalSummary(typeOfJournal, date)
            }
        )
        
        Spacer(modifier = Modifier.height(14.dp))
        JournalTypeDropdown(onClick = {
            typeOfJournal = it
            viewModel.getJournalSummary(it, date)
        })
        
        Spacer(modifier = Modifier.height(18.dp))

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = Color.Blue,
                )
            }
        } else if (state.error.isNotBlank()) {
            Log.d("gagal", state.error)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)

                )
            }
        } else {
            Text(
                text = "Jurnal $typeOfJournal",
                fontFamily = Constant.LATO_FONT_FAMILY,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(18.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.journalSummaries) { journalSummary ->
                    JournalSummaryItem(
                        journalSummaryData = journalSummary,
                        onCardClick = {
                            println(Screen.TeacherActivityDetailScreen.route +
                                    "/${journalSummary.activity.id}" + "/$date")
                            navController.navigate(
                                Screen.TeacherActivityDetailScreen.route +
                                    "/${journalSummary.activity.id}" + "/$date"
                            )
                        }
                    )
                }
            }
        }
    }
}