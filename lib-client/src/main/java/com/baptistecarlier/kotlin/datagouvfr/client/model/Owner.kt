package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property description The message author (optional)
 * @property `class` The object class (required)
 * @property id The object unique identifier (required)
 * @property avatar The user avatar URL (optional)
 * @property avatarThumbnail The user avatar thumbnail URL. This is the square (500x500) and cropped version. (optional)
 * @property firstName The user first name (optional)
 * @property lastName The user larst name (optional)
 * @property page The user profile page URL (optional)
 * @property slug The user permalink string (required)
 * @property uri The user API URI (required)
 */
@Serializable
data class Owner(
    @SerialName("description")
    var description: String? = null,
    @SerialName("class")
    var `class`: String,
    @SerialName("id")
    var id: String,
    @SerialName("avatar")
    var avatar: String? = null,
    @SerialName("avatar_thumbnail")
    var avatarThumbnail: String? = null,
    @SerialName("first_name")
    var firstName: String? = null,
    @SerialName("last_name")
    var lastName: String? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("slug")
    var slug: String,
    @SerialName("uri")
    var uri: String,
)
