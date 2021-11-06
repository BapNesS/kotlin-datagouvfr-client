package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The current transfer request status
 * Is it validated or not
 * Values: PENDING, ACCEPTED, REFUSED
 */
@Serializable
enum class StatusEnum(val value: String) {
    @SerialName("pending")
    PENDING("pending"),
    @SerialName("accepted")
    ACCEPTED("accepted"),
    @SerialName("refused")
    REFUSED("refused")
}