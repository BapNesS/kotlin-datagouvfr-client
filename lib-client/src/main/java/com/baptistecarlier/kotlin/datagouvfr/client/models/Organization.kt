package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property acronym The organization acronym
 * @property badges The organization badges
 * @property createdAt The organization creation date
 * @property deleted The organization deletion date if deleted
 * @property description The organization description in Markdown
 * @property id The organization identifier
 * @property lastModified The organization last modification date
 * @property logo The organization logo URL
 * @property logoThumbnail The organization logo thumbnail URL. This is the square (100x100) and cropped version.
 * @property members
 * @property metrics The organization metrics
 * @property name The organization name
 * @property page The organization page URL
 * @property slug The organization string used as permalink
 * @property uri The organization API URI
 * @property url The organization website URL
 */
@Serializable
data class Organization(
    @SerialName("description")
    var description: String? = null,
    @SerialName("id")
    var id: String,
    @SerialName("name")
    var name: String,
    @SerialName("slug")
    var slug: String,
    @SerialName("acronym")
    var acronym: String? = null,
    @SerialName("badges")
    var badges: List<Badge>? = null,
    @SerialName("created_at")
    var createdAt: LocalDateTime? = null,
    @SerialName("deleted")
    var deleted: LocalDateTime? = null,
    @SerialName("last_modified")
    var lastModified: LocalDateTime? = null,
    @SerialName("logo")
    var logo: String? = null,
    @SerialName("logo_thumbnail")
    var logoThumbnail: String? = null,
    //@SerialName("members")
    //var members: List<Map<String, Any?>>? = null,
    @SerialName("metrics")
    var metrics: Metrics? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("uri")
    var uri: String? = null,
    @SerialName("url")
    var url: String? = null
)