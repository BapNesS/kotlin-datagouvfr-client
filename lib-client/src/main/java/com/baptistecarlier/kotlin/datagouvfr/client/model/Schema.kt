package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The schema identifier
 * @property label The schema display name
 * @property versions The available versions of the schema
 */
@Serializable
data class Schema(
    @SerialName("id")
    var id: String? = null,
    @SerialName("label")
    var label: String? = null,
    @SerialName("versions")
    var versions: List<String>? = null
)