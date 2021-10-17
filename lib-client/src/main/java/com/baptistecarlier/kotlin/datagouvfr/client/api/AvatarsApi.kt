package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import kotlinx.coroutines.flow.Flow

/**
 * Get a deterministic avatar given an identifier at a given size
 */
interface AvatarsApi {

    /**
     * @param identifier
     * @param size
     */
    fun getAvatar(
        identifier: String,
        size: Int
    ): Flow<DgfrResource<ByteArray>>

}