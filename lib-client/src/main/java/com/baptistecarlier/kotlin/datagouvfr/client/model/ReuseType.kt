package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The reuse type identifier
 * @property label The reuse type display name
 */
@Serializable
data class ReuseType(
    @SerialName("id")
    var id: String? = null,
    @SerialName("label")
    var label: String? = null
)