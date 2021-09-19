package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property follower The follower
 * @property id The follow object technical ID
 * @property since The date from which the user started following
 */
@Serializable
data class Follow(
    @SerialName("follower")
    var follower: List<User>? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("since")
    var since: LocalDateTime? = null
)