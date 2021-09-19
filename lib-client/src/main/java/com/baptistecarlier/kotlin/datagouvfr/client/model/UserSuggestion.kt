package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property avatarUrl The user avatar URL
 * @property firstName The user first name
 * @property id The user identifier
 * @property lastName The user last name
 * @property score The internal match score
 * @property slug The user permalink string
 */
@Serializable
data class UserSuggestion(
    @SerialName("avatar_url")
    var avatarUrl: String? = null,
    @SerialName("first_name")
    var firstName: String? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("last_name")
    var lastName: String? = null,
    @SerialName("score")
    var score: Long? = null,
    @SerialName("slug")
    var slug: String? = null
)
