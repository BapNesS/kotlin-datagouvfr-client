package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @property createdAt The error creation date
 * @property details Optional details (ie. stacktrace)
 * @property message The error short message
 */
@Serializable
data class HarvestError(
    @SerialName("created_at")
    var createdAt: LocalDateTime,
    @SerialName("message")
    var message: String,
    @SerialName("details")
    var details: String? = null
)


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