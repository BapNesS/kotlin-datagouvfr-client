package com.baptistecarlier.kotlin.datagouvfr.client.api

import kotlinx.coroutines.flow.Flow

/**
 * Get a deterministic avatar given an identifier at a given size
 */
interface AvatarsApi {

    /**
     * @param identifier
     * @param size
     */
    suspend fun getAvatar(
        identifier: String,
        size: Int
    ): Flow<ByteArray?>

}