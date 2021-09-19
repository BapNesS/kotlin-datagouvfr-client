package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The granularity identifier
 * @property name The granularity name
 */
@Serializable
data class GeoGranularity(
    @SerialName("id")
    var id: String,
    @SerialName("name")
    var name: String
)