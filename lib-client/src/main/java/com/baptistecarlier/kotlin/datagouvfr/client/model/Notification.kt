package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property createdOn The notification creation datetime
 * @property type The notification type
 */
@Serializable
data class Notification(
    @SerialName("created_on")
    var createdOn: String,
    @SerialName("details")
    var details: Map<String, String>,
    @SerialName("type")
    var type: String
)
