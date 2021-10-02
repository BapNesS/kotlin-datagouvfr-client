package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The role identifier
 * @property label The role label
 */
@Serializable
data class OrganizationRole(
    @SerialName("id")
    var id: String? = null,
    @SerialName("label")
    var label: String? = null
)