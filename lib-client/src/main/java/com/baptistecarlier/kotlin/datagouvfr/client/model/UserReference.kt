package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property `class` The object class
 * @property id The object unique identifier
 * @property avatar The user avatar URL
 * @property avatarThumbnail The user avatar thumbnail URL. This is the square (500x500) and cropped version.
 * @property firstName The user first name
 * @property lastName The user larst name
 * @property page The user profile page URL
 * @property slug The user permalink string
 * @property uri The user API URI
 */
@Serializable
data class UserReference(
    @SerialName("class")
    var `class`: String,
    @SerialName("id")
    var id: String,
    @SerialName("slug")
    var slug: String,
    @SerialName("uri")
    var uri: String,
    @SerialName("avatar")
    var avatar: String? = null,
    @SerialName("avatar_thumbnail")
    var avatarThumbnail: String? = null,
    @SerialName("first_name")
    var firstName: String? = null,
    @SerialName("last_name")
    var lastName: String? = null,
    @SerialName("page")
    var page: String? = null
)