package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.User
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Connected user related operations
 */
interface MeApi: WithApiKey {

    /**
     * Fetch the current user (me) identity
     */
    suspend fun me(): Flow<User?>
}

