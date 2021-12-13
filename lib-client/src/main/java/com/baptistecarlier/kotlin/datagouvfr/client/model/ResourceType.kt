package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The resource type identifier
 * @property label The resource type display name
 */
@Serializable
data class ResourceType(
    @SerialName("id")
    var id: String? = null,
    @SerialName("label")
    var label: String? = null
)
