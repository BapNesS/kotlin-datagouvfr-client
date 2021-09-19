package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property content The message body
 * @property postedBy The message author
 * @property postedOn The message posting date
 */
@Serializable
data class DiscussionMessage(
    @SerialName("content")
    var content: String? = null,
    @SerialName("posted_by")
    var postedBy: DiscussionMessageAuthor? = null,
    @SerialName("posted_on")
    var postedOn: LocalDateTime? = null
)
