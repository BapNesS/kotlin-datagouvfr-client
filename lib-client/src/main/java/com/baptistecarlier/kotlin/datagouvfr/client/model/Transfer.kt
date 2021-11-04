package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.acl.Owner

/**
 * @property comment A comment about the transfer request
 * @property created The transfer request date
 * @property id The transfer unique identifier
 * @property owner The user or organization currently owning the transfered object
 * @property recipient The user or organization receiving the transfered object
 * @property reponseComment A comment about the transfer response
 * @property responded The transfer response date
 * @property status The current transfer request status
 * @property subject The transfered object
 */
@Serializable
data class Transfer(
    @SerialName("comment")
    var comment: String? = null,
    @SerialName("created")
    var created: LocalDateTime? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("owner")
    var owner: Owner? = null,
    @SerialName("recipient")
    var recipient: TransferRequestRecipient? = null,
    @SerialName("reponse_comment")
    var reponseComment: String? = null,
    @SerialName("responded")
    var responded: LocalDateTime? = null,
    @SerialName("status")
    var status: Transfer.StatusEnum? = null,
    @SerialName("subject")
    var subject: TransferRequestSubject? = null
) {
    /**
     * The current transfer request status
     * Values: PENDING, ACCEPTED, REFUSED
     */
    @Serializable
    enum class StatusEnum(val value: String) {
        @SerialName("pending")
        PENDING("pending"),
        @SerialName("accepted")
        ACCEPTED("accepted"),
        @SerialName("refused")
        REFUSED("refused")
    }
}
