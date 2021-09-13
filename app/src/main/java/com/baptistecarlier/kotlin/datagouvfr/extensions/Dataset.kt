package com.baptistecarlier.kotlin.datagouvfr.extensions

import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset

fun Dataset.truncatedDescription(): String = with(description) {
    val maxLength = 300
    if (this.length > maxLength) {
        this.take(maxLength) + "…"
    } else {
        this
    }.replace("\n", " ")
}


fun Dataset.getTagsOrNull() =
    tags?.joinToString(separator = ", ")?.nullIfEmpty()

fun Dataset.getAuthor() =
    organization?.name?.let {
        it
    } ?: run {
        owner?.displayName().orEmpty()
    }