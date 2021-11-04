package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property badges The reuse badges
 * @property createdAt The reuse creation date
 * @property datasets The reused datasets
 * @property deleted The organization identifier
 * @property description The reuse description in Markdown
 * @property featured Is the reuse featured
 * @property id The reuse identifier
 * @property image The reuse thumbnail thumbnail (cropped) URL
 * @property imageThumbnail The reuse thumbnail thumbnail URL. This is the square (100x100) and cropped version.
 * @property lastModified The reuse last modification date
 * @property metrics The reuse metrics
 * @property organization The publishing organization
 * @property owner The owner user
 * @property page The reuse page URL
 * @property `private` Is the reuse private to the owner or the organization
 * @property slug The reuse permalink string
 * @property tags Some keywords to help in search
 * @property title The reuse title
 * @property typeEnum The reuse type
 * @property uri The reuse API URI
 * @property url The reuse remote URL (website)
 */
@Serializable
data class Reuse(
    @SerialName("description")
    var description: String,
    @SerialName("title")
    var title: String,
    @SerialName("type")
    var typeEnum: Reuse.TypeEnum,
    @SerialName("url")
    var url: String,
    @SerialName("badges")
    var badges: List<Badge>? = null,
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
    @SerialName("image")
    var image: String? = null,
    @SerialName("image_thumbnail")
    var imageThumbnail: String? = null,
    @SerialName("last_modified")
    var lastModified: LocalDateTime? = null,
    @SerialName("metrics")
    var metrics: Metrics? = null,
    @SerialName("organization")
    var organization: Organization? = null,
    @SerialName("owner")
    var owner: User? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("private")
    var `private`: Boolean? = null,
    @SerialName("slug")
    var slug: String? = null,
    @SerialName("tags")
    var tags: List<String>? = null,
    @SerialName("uri")
    var uri: String? = null
) {
    /**
     * The reuse type
     * Values: API, APPLICATION, IDEA, NEWS_ARTICLE, PAPER, POST, VISUALIZATION, HARDWARE
     */
    @Serializable
    enum class TypeEnum(val value: String) {
        @SerialName("api")
        API("api"),
        @SerialName("application")
        APPLICATION("application"),
        @SerialName("idea")
        IDEA("idea"),
        @SerialName("news_article")
        NEWS_ARTICLE("news_article"),
        @SerialName("paper")
        PAPER("paper"),
        @SerialName("post")
        POST("post"),
        @SerialName("visualization")
        VISUALIZATION("visualization"),
        @SerialName("hardware")
        HARDWARE("hardware")
    }
}
