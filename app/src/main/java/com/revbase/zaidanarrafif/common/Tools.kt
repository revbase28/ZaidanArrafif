package com.revbase.zaidanarrafif.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
}