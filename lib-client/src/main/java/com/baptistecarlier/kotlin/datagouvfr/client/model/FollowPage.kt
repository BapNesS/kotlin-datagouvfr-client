package com.baptistecarlier.kotlin.datagouvfr.client.model

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property `data` The page data
 * @property facets Search facets results if any
 * @property nextPage The next page URL if exists
 * @property page The current page
 * @property pageSize The page size used for pagination
 * @property previousPage The previous page URL if exists
 * @property total The total paginated items
 */
@MissingFieldMapping
@Serializable
data class FollowPage(
    @SerialName("page")
    var page: Int,
    @SerialName("page_size")
    var pageSize: Int,
    @SerialName("total")
    var total: Int,
    @SerialName("data")
    var `data`: List<Follow>? = null,
    /*@SerialName("facets")
    var facets: Map<String, Any?>? = null,*/
    @SerialName("next_page")
    var nextPage: String? = null,
    @SerialName("previous_page")
    var previousPage: String? = null
)
