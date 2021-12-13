package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property code The territory main code
 * @property id The territory identifier
 * @property keys The territory known codes
 * @property level The territory administrative level
 * @property name The territory name
 * @property score The internal match score
 */
@Serializable
data class TerritorySuggestion(
    @SerialName("code")
    var code: String,
    @SerialName("id")
    var id: String,
    @SerialName("level")
    var level: String,
    @SerialName("name")
    var name: String,
    @SerialName("score")
    var score: Long,
    @SerialName("keys")
    var keys: TerritorySuggestionKey? = null
)
