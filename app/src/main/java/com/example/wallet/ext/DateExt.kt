package com.example.wallet.ext

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getFormatterForTime(): DateTimeFormatter {
    return DateTimeFormatter.ofPattern(Patterns.HOUR_MIN_MERIDIEM)
}

fun LocalDate.getFormattedDay(): String {
    val formatter =
        DateTimeFormatter.ofPattern(Patterns.MONTH_DAY_PATTERN).withLocale(Locale.getDefault())
    return format(formatter)
}

fun LocalDateTime.getFormattedFullDateTime(): String {
    val formatter =
        DateTimeFormatter.ofPattern(Patterns.YEAR_MONTH_DAY).withLocale(Locale.getDefault())
    return atZone(ZoneId.systemDefault()).format(formatter)
}

object Patterns {
    const val MONTH_DAY_PATTERN = "LLLL dd"
    const val HOUR_MIN_MERIDIEM = "hh:mm a"
    const val YEAR_MONTH_DAY = "y-MM-dd hh:mm:s a"
}