package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property content The message body
 * @property postedBy The message author
 * @property postedOn The message posting date
 */
@Serializable
data class IssueMessage(
    @SerialName("content")
    var content: String,
    /*@SerialName("posted_by")
    var postedBy: Map<String, Any?>,*/
    @SerialName("posted_on")
    var postedOn: LocalDateTime
)
