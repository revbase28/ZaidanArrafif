package com.revbase.zaidanarrafif.presentation.teacher.jurnal_screen.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker
import com.revbase.zaidanarrafif.common.Tools
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePicker(
    currentDate: String,
    onDateClick: (String) -> Unit
) {
    val activity = LocalContext.current

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val now = Calendar.getInstance()
    val dateString = dateFormat.parse(currentDate)
    if (dateString != null)
        now.time = dateString
    val mYear = now.get(Calendar.YEAR)
    val mMonth = now.get(Calendar.MONTH)
    val mDay = now.get(Calendar.DAY_OF_MONTH)

    var date by remember { mutableStateOf(currentDate) }
    val datePicker = DatePickerDialog(
        activity,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            date = dateFormat.format(cal.time)
            onDateClick(date)
        }, mYear, mMonth, mDay
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(vertical = 8.dp)
            .clickable {
                datePicker.show()
            }
    ) {
        Text(
            text = Tools.dateTimeStringToString(date),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}