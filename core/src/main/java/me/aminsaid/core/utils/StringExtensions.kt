package me.aminsaid.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

// string
fun String.getDayOfWeek(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("EEEE", Locale.US)
    return outputFormat.format(date!!)
}

fun String.getDayOfWeekWithTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
    val date = inputFormat.parse(this)

    val outputFormat = SimpleDateFormat("EEEE, HH:mm a", Locale.US)
    return outputFormat.format(date!!)
}