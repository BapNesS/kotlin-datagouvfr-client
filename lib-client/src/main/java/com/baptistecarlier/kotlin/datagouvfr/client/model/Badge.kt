package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property kind Kind of badge (certified, etc), specific to each model
 */
@Serializable
data class Badge(
    @SerialName("kind")
    var kind: String
)