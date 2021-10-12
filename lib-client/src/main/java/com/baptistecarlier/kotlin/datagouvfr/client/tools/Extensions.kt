package com.baptistecarlier.kotlin.datagouvfr.client.tools

import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.javaio.*

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

internal fun StringBuilder.urlEncore(): String = this.toString().encodeURLPath()

internal fun HttpRequestBuilder.addApiKey(apiKey: String) {
    header("X-API-KEY", apiKey)
}

internal fun ByteReadChannel?.readAndClose(): String? {
    return this?.toInputStream()?.bufferedReader().use { it?.readText() }
}

internal fun FormBuilder.fbAppendIfNotNull(key: String, value: Int?) {
    if (value != null) {
        append(key, value)
    }
}

internal fun FormBuilder.fbAppendIfNotNull(key: String, value: String?) {
    if (value != null) {
        append(key, value)
    }
}