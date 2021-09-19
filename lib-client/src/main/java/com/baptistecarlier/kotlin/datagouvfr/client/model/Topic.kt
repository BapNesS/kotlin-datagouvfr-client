package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property createdAt The topic creation date
 * @property datasets The topic datasets
 * @property deleted The organization identifier
 * @property description The topic description in Markdown
 * @property featured Is the topic featured
 * @property id The topic identifier
 * @property lastModified The topic last modification date
 * @property name The topic name
 * @property owner The owner user
 * @property page The topic page URL
 * @property `private` Is the topic private
 * @property reuses The topic reuses
 * @property slug The topic permalink string
 * @property tags Some keywords to help in search
 * @property uri The topic API URI
 */
@Serializable
data class Topic(
    @SerialName("description")
    var description: String,
    @SerialName("name")
    var name: String,
    @SerialName("tags")
    var tags: List<String>,
    @SerialName("created_at")
    var createdAt: LocalDateTime? = null,
    @SerialName("datasets")
    var datasets: List<Dataset>? = null,
    @SerialName("deleted")
    var deleted: LocalDateTime? = null,
    @SerialName("featured")
    var featured: Boolean? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("last_modified")
    var lastModified: LocalDateTime? = null,
    @SerialName("owner")
    var owner: Owner? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("private")
    var `private`: Boolean? = null,
    @SerialName("reuses")
    var reuses: List<Reuse>? = null,
    @SerialName("slug")
    var slug: String? = null,
    @SerialName("uri")
    var uri: String? = null
)
