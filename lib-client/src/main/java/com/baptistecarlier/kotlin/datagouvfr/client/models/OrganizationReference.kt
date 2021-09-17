package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property `class` The object class
 * @property id The object unique identifier
 * @property acronym The organization acronym
 * @property badges The organization badges
 * @property logo The organization logo URL
 * @property logoThumbnail The organization logo thumbnail URL. This is the square (100x100) and cropped version.
 * @property name The organization name
 * @property page The organization web page URL
 * @property slug The organization string used as permalink
 * @property uri The organization API URI
 */
@Serializable
data class OrganizationReference(
    @SerialName("class")
    var `class`: String,
    @SerialName("id")
    var id: String,
    @SerialName("slug")
    var slug: String,
    @SerialName("acronym")
    var acronym: String? = null,
    @SerialName("badges")
    var badges: List<Badge>? = null,
    @SerialName("logo")
    var logo: String? = null,
    @SerialName("logo_thumbnail")
    var logoThumbnail: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("uri")
    var uri: String? = null
)