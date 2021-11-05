package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property acronym An optional dataset acronym
 * @property id The dataset identifier
 * @property imageUrl The dataset (organization) logo URL
 * @property page The web page URL for this dataset
 * @property score The internal match score
 * @property slug The dataset permalink string
 * @property title The dataset title
 */
@Serializable
data class DatasetSuggestion(
    @SerialName("acronym")
    var acronym: String? = null,
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
