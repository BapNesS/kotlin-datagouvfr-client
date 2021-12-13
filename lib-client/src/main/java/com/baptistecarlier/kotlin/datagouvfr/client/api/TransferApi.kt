package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.model.Transfer
import com.baptistecarlier.kotlin.datagouvfr.client.model.TransferRequest
import com.baptistecarlier.kotlin.datagouvfr.client.model.TransferResponse
import kotlinx.coroutines.flow.Flow

interface TransferApi : WithApiKey {

    /**
     * Fetch a transfer request given its identifier
     * @param id (required)
     */
    fun getTransfer(id: String): Flow<DgfrCallState<Transfer>>

    /**
     * List all transfer requests
     */
    fun getListTransfers(): Flow<DgfrCallState<List<Transfer>>>

    /**
     * Initiate transfer request
     * @param payload (required)
     */
    fun postRequestTransfer(payload: TransferRequest): Flow<DgfrCallState<Transfer>>

    /**
     * Respond to a transfer request
     * @param id (required)
     * @param payload (required)
     */
    fun postRespondToTransfer(id: String, payload: TransferResponse): Flow<DgfrCallState<Transfer>>
}
