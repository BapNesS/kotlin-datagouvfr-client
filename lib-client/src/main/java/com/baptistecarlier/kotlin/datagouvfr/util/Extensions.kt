package com.baptistecarlier.kotlin.datagouvfr.util

internal fun StringBuilder.appendIfNotNull(key: String, value: List<String>?) {
    if (value != null) {
        this.append("$key=$value&")
    }
}

internal fun StringBuilder.appendIfNotNull(key: String, value: String?) {
    if (value != null) {
        this.append("$key=$value&")
    }
}

internal fun StringBuilder.appendIfNotNull(key: String, value: Boolean?) {
    if (value != null) {
        this.append("$key=$value&")
    }
}

internal fun StringBuilder.appendIfNotNull(key: String, value: Int?) {
    if (value != null) {
        this.append("$key=$value&")
    }
}