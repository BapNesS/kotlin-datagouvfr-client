package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property features The backend optional features
 * @property filters The backend supported filters
 * @property id The backend identifier
 * @property label The backend display name
 */
@Serializable
data class HarvestBackend(
    @SerialName("features")
    var features: List<HarvestFeature>? = null,
    @SerialName("filters")
    var filters: List<HarvestFilter>? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("label")
    var label: String? = null
)
