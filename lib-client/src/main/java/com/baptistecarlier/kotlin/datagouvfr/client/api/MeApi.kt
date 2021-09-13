package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.models.User
import kotlinx.coroutines.flow.Flow

interface MeApi: WithApiKey {

    suspend fun me(): Flow<User?>

}

