package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property id The reuse identifier
 * @property imageUrl The reuse thumbnail URL
 * @property page The reuse page URL
 * @property score The internal match score
 * @property slug The reuse permalink string
 * @property title The reuse title
 */
@Serializable
data class ReuseSuggestion(
    @SerialName("id")
    var id: String? = null,
    @SerialName("image_url")
    var imageUrl: String? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("score")
    var score: Long? = null,
    @SerialName("slug")
    var slug: String? = null,
    @SerialName("title")
    var title: String? = null
)