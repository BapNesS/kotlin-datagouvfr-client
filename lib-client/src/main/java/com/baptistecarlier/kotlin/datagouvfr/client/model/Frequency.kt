package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @property id The frequency identifier
 * @property label The frequency display name
 */
@Serializable
data class Frequency(
    @SerialName("id")
    var id: String? = null,
    @SerialName("label")
    var label: String? = null
)