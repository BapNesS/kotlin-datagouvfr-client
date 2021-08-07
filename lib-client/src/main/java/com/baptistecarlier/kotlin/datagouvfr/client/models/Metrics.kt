package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property discussions
 * @property followers
 * @property issues
 * @property reuses
 * @property views
 */
@Serializable
data class Metrics(
    @SerialName("discussions")
    var discussions: Int = 0,
    @SerialName("followers")
    var followers: Int = 0,
    @SerialName("issues")
    var issues: Int = 0,
    @SerialName("reuses")
    var reuses: Int = 0,
    @SerialName("views")
    var views: Int = 0
)