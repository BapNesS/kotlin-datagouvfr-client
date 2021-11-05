package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The Site unique identifier
 * @property metrics The associated metrics
 * @property title The site display title
 */
@Serializable
data class Site(
    @SerialName("id")
    var id: String,
    @SerialName("title")
    var title: String,
    @SerialName("metrics")
    var metrics: Metrics? = null
)
