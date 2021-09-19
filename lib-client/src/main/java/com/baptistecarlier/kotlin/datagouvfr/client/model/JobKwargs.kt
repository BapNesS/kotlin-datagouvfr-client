package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property description Thhe job execution keyword arguments
 */
@Serializable
data class JobKwargs(
    @SerialName("description")
    var description: String,
)