package com.revbase.zaidanarrafif.common

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object Tools {
    fun dateTimeStringToString(dateTimeString: String): String{
        val listOfMonthInIndonesia = listOf<String>(
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
        )

        val dateSplitTime = dateTimeString.split(" ");
        val date = LocalDate.parse(dateSplitTime[0], DateTimeFormatter.ISO_DATE)

        return "${date.dayOfMonth} ${listOfMonthInIndonesia[date.monthValue - 1]} ${date.year}"
    }

    fun getTimeStampAsString(): String {
        val timeStamp = System.currentTimeMillis()
        val date = Date(timeStamp)
        val format = SimpleDateFormat("yyMMdd HH:mm")
        return format.format(date).toString()
    }

    fun intToHourMinuteFormat(value: Int): String {
        val minute = value/60
        val second = if(minute == 0) value else value % 60

        return minute.toString().padStart(2, '0') + ":" + second.toString().padStart(2, '0')
    }
}