package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property default The feature default state (true is enabled)
 * @property description Some details about the behavior
 * @property key The feature key
 * @property label A localized human-readable and descriptive label
 */
@Serializable
data class HarvestFeature(
    @SerialName("default")
    var default: String? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("key")
    var key: String? = null,
    @SerialName("label")
    var label: String? = null
)
