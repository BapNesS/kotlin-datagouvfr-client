package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @property description The filter details
 * @property key The filter key
 * @property label A localized human-readable label
 * @property type The filter expected type
 */
@Serializable
data class HarvestFilter(
    @SerialName("description")
    var description: String? = null,
    @SerialName("key")
    var key: String? = null,
    @SerialName("label")
    var label: String? = null,
    @SerialName("type")
    var type: String? = null
)