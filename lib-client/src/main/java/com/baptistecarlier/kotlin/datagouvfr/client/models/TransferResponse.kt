package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property comment An optional comment about the transfer response
 * @property response The response
 */
@Serializable
data class TransferResponse(
    @SerialName("response")
    var response: TransferResponse.ResponseEnum,
    @SerialName("comment")
    var comment: String? = null
) {
    /**
     * The response
     * Values: ACCEPT, REFUSE
     */
    @Serializable
    enum class ResponseEnum(val value: String) {
        @SerialName("accept")
        ACCEPT("accept"),
        @SerialName("refuse")
        REFUSE("refuse")
    }
}
