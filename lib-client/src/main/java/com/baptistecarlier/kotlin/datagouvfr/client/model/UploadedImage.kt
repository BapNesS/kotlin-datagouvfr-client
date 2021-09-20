package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property image
 * @property success Whether the upload succeeded or not.
 */
@Serializable
data class UploadedImage(
    @SerialName("image")
    var image: String? = null,
    @SerialName("success")
    var success: Boolean? = null
)