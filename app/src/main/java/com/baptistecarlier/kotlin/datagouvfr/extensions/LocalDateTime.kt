package com.baptistecarlier.kotlin.datagouvfr.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

enum class DateFormat(val format: String) {
    DDMMYYYY("dd / MM / YYYY")
}

fun LocalDateTime.date(pattern: DateFormat = DateFormat.DDMMYYYY): String {
    val javaLocalDateTime = this.toJavaLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern(pattern.format)
    return formatter.format(javaLocalDateTime).orEmpty()
}