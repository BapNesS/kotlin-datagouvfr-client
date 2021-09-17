package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property comment An explanation about the transfer request
 * @property recipient The transfer recipient, either an user or an organization
 * @property subject The transfered subject
 */
@Serializable
data class TransferRequest(
    @SerialName("comment")
    var comment: String,
    @SerialName("recipient")
    var recipient: TransferRequestRecipient,
    @SerialName("subject")
    var subject: TransferRequestSubject
)