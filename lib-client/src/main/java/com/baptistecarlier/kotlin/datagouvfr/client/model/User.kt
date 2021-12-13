package com.baptistecarlier.kotlin.datagouvfr.client.model

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property about The user self description
 * @property active
 * @property avatar The user avatar URL
 * @property avatarThumbnail The user avatar thumbnail URL. This is the square (500x500) and cropped version.
 * @property email The user email
 * @property firstName The user first name
 * @property id The user identifier
 * @property lastName The user last name
 * @property metrics The user metrics
 * @property organizations The organization the user belongs to
 * @property page The user profile page URL
 * @property roles Site wide user roles
 * @property since The registeration date
 * @property slug The user permalink string
 * @property uri The user API URI
 * @property website The user website
 */
@MissingFieldMapping
@Serializable
data class User(
    @SerialName("first_name")
    var firstName: String,
    @SerialName("id")
    var id: String,
    @SerialName("last_name")
    var lastName: String,
    @SerialName("since")
    var since: LocalDateTime? = null,
    @SerialName("slug")
    var slug: String,
    @SerialName("uri")
    var uri: String,
    @SerialName("about")
    var about: String? = null,
    @SerialName("active")
    var active: Boolean? = null,
    @SerialName("avatar")
    var avatar: String? = null,
    @SerialName("avatar_thumbnail")
    var avatarThumbnail: String? = null,
    // @SerialName("email")
    // var email: Map<String, Any?>? = null,
    // @SerialName("metrics")
    // var metrics: Map<String, Any?>? = null,
    @SerialName("organizations")
    var organizations: List<OrganizationReference>? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("roles")
    var roles: List<String>? = null,
    @SerialName("website")
    var website: String? = null
)
