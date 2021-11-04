package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property acronym The organization acronym
 * @property id The organization identifier
 * @property imageUrl The organization logo URL
 * @property name The organization name
 * @property page The organization web page URL
 * @property score The internal match score
 * @property slug The organization permalink string
 */
@Serializable
data class OrganizationSuggestion(
    @SerialName("acronym")
    var acronym: String? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("image_url")
    var imageUrl: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("score")
    var score: Long? = null,
    @SerialName("slug")
    var slug: String? = null
)
