package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.models.Tag
import kotlinx.coroutines.flow.Flow

/**
 * Tags related operations
 */
interface TagsApi {

    /**
     * @param q The string to autocomplete/suggest
     * @param size The amount of suggestion to fetch
     */
    suspend fun getTagsSuggest(
        q: String,
        size: Int? = null
    ): Flow<List<Tag>?>

}


