package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property alternateTitles Same alternative known titles (improve rematch)
 * @property alternateUrls Same alternative known URLs (improve rematch)
 * @property flags Some arbitry flags
 * @property id The license identifier
 * @property maintainer The license official maintainer
 * @property title The resource title
 * @property url The license official URL
 */
@Serializable
data class License(
    @SerialName("id")
    var id: String,
    @SerialName("title")
    var title: String,
    @SerialName("alternate_titles")
    var alternateTitles: List<String>? = null,
    @SerialName("alternate_urls")
    var alternateUrls: List<String>? = null,
    @SerialName("flags")
    var flags: List<String>? = null,
    @SerialName("maintainer")
    var maintainer: String? = null,
    @SerialName("url")
    var url: String? = null
)