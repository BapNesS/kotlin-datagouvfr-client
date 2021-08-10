package com.baptistecarlier.kotlin.datagouvfr.extensions

import kotlin.String

fun String.nullIfEmpty(): String? {
    return if (this.isEmpty()) { null } else { this }
}