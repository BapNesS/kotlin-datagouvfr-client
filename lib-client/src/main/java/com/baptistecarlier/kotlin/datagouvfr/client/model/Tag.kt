package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property score
 * @property text
 */
@Serializable
data class Tag(
    @SerialName("score")
    var score: Double,
    @SerialName("text")
    var text: String
)
