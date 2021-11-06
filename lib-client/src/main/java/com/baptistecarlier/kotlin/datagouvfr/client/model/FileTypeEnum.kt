package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Whether the resource is an uploaded file, a remote file or an API
 * Values: FILE, REMOTE
 */
@Serializable
enum class FileTypeEnum(val value: String) {
    @SerialName("file") FILE("file"),
    @SerialName("remote") REMOTE("remote")
}
