package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property description The transfered subject
 * @property `class` The object class
 * @property id The object unique identifier
 */
@Serializable
data class TransferRequestSubject(
    @SerialName("description")
    var description: String? = null,
    @SerialName("class")
    var `class`: String? = null,
    @SerialName("id")
    var id: String? = null,
)