package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property comment A request comment from the user
 * @property created The request creation date
 * @property id
 * @property status The current request status
 * @property user
 */
@Serializable
data class MembershipRequest(
    @SerialName("comment")
    var comment: String,
    @SerialName("status")
    var status: StatusEnum,
    @SerialName("created")
    var created: LocalDateTime? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("user")
    var user: UserReference? = null
)
