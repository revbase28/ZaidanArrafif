package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen

import android.graphics.fonts.FontStyle
import android.text.format.DateFormat.format
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.revbase.zaidanarrafif.common.Constant
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component.JournalSummaryItem
import com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component.JournalTypeDropdown
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun TeacherJournalScreen() {
    val date = SimpleDateFormat("dd-MM-yyyy", Locale.US).format(Date())
    var typeOfJournal by remember { mutableStateOf("Jurnal Kegiatan") }
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = date,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(14.dp))
        JournalTypeDropdown(onClick = {
            typeOfJournal = it
        })
        
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = typeOfJournal,
            fontFamily = Constant.LATO_FONT_FAMILY,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(18.dp))
        
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                JournalSummaryItem()
            }

            item {
                JournalSummaryItem()
            }
        }
    }
}