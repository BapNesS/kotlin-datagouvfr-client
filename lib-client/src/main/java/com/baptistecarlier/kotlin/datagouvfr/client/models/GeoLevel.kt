package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The level identifier
 * @property name The level name
 * @property parents The parent levels
 */
@Serializable
data class GeoLevel(
    @SerialName("id")
    var id: String,
    @SerialName("name")
    var name: String,
    @SerialName("parents")
    var parents: List<String>? = null
)