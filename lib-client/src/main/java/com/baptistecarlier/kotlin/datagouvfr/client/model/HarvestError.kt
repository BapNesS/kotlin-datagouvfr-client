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
