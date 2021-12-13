package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property content The post content in Markdown
 * @property createdAt The post creation date
 * @property creditTo An optional credit line (associated to the image)
 * @property creditUrl An optional link associated to the credits
 * @property datasets The post datasets
 * @property headline The post headline
 * @property id The post identifier
 * @property image The post image
 * @property lastModified The post last modification date
 * @property name The post name
 * @property owner The owner user
 * @property page The post page URL
 * @property published The post publication date
 * @property reuses The post reuses
 * @property slug The post permalink string
 * @property tags Some keywords to help in search
 * @property uri The post API URI
 */
@Serializable
data class Post(
    @SerialName("content")
    var content: String,
    @SerialName("headline")
    var headline: String,
    @SerialName("name")
    var name: String,
    @SerialName("created_at")
    var createdAt: LocalDateTime? = null,
    @SerialName("credit_to")
    var creditTo: String? = null,
    @SerialName("credit_url")
    var creditUrl: String? = null,
    @SerialName("datasets")
    var datasets: List<Dataset>? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("image")
    var image: String? = null,
    @SerialName("last_modified")
    var lastModified: LocalDateTime? = null,
    @SerialName("owner")
    var owner: Owner? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("published")
    var published: LocalDateTime? = null,
    @SerialName("reuses")
    var reuses: List<Reuse>? = null,
    @SerialName("slug")
    var slug: String? = null,
    @SerialName("tags")
    var tags: List<String>? = null,
    @SerialName("uri")
    var uri: String? = null
)
