package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property description The transfer recipient, either an user or an organization
 * @property `class` The object class
 * @property id The object unique identifier
 */
@Serializable
data class TransferRequestRecipient(
    @SerialName("description")
    var description: String? = null,
    @SerialName("class")
    var `class`: String? = null,
    @SerialName("id")
    var id: String? = null,
)