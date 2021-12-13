package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Tag
import kotlinx.coroutines.flow.Flow

/**
 * Tags related operations
 */
interface TagsApi {

    /**
     * @param q The string to autocomplete/suggest
     * @param size The amount of suggestion to fetch
     */
    fun getTagsSuggest(
        q: String,
        size: Int? = null
    ): Flow<DgfrResource<List<Tag>>>
}
