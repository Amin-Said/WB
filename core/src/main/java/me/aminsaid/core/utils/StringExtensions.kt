package me.aminsaid.core.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

// string
fun String.getDayOfWeek(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
    inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Set a specific timezone (e.g., UTC)
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("EEEE", Locale.US)
    outputFormat.timeZone = TimeZone.getTimeZone("UTC") // Set the same timezone
    return outputFormat.format(date!!)
}

fun String.getDayOfWeekWithTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
    inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Set a specific timezone (e.g., UTC)
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("EEEE, HH:mm a", Locale.US)
    outputFormat.timeZone = TimeZone.getTimeZone("UTC") // Set the same timezone
    return outputFormat.format(date!!)
}