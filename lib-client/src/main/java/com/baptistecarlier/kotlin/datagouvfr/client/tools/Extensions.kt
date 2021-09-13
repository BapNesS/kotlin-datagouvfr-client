package com.baptistecarlier.kotlin.datagouvfr.client.tools

import io.ktor.client.request.*
import io.ktor.http.*

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