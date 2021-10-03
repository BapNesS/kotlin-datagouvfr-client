package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Transfer
import com.baptistecarlier.kotlin.datagouvfr.client.model.TransferRequest
import com.baptistecarlier.kotlin.datagouvfr.client.model.TransferResponse
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

interface TransferApi: WithApiKey {

    /**
     * Fetch a transfer request given its identifier
     * @param id (required)
     */
    suspend fun getTransfer(id: String): Flow<DgfrResource<Transfer>>

    /**
     * List all transfer requests
     */
    suspend fun getListTransfers(): Flow<DgfrResource<List<Transfer>>>

    /**
     * Initiate transfer request
     * @param payload (required)
     */
    suspend fun postRequestTransfer(payload: TransferRequest): Flow<DgfrResource<Transfer>>

    /**
     * Respond to a transfer request
     * @param id (required)
     * @param payload (required)
     */
    suspend fun postRespondToTransfer(id: String, payload: TransferResponse): Flow<DgfrResource<Transfer>>

}