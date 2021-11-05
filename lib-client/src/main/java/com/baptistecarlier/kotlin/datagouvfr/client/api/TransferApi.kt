package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Transfer
import com.baptistecarlier.kotlin.datagouvfr.client.model.TransferRequest
import com.baptistecarlier.kotlin.datagouvfr.client.model.TransferResponse
import kotlinx.coroutines.flow.Flow

internal interface TransferApi : WithApiKey {

    /**
     * Fetch a transfer request given its identifier
     * @param id (required)
     */
    fun getTransfer(id: String): Flow<DgfrResource<Transfer>>

    /**
     * List all transfer requests
     */
    fun getListTransfers(): Flow<DgfrResource<List<Transfer>>>

    /**
     * Initiate transfer request
     * @param payload (required)
     */
    fun postRequestTransfer(payload: TransferRequest): Flow<DgfrResource<Transfer>>

    /**
     * Respond to a transfer request
     * @param id (required)
     * @param payload (required)
     */
    fun postRespondToTransfer(id: String, payload: TransferResponse): Flow<DgfrResource<Transfer>>
}
