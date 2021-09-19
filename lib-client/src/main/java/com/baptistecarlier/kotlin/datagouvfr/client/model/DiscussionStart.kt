package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property comment The content of the initial comment
 * @property extras Extras attributes as key-value pairs
 * @property subject The discussion target object
 * @property title The title of the discussion to open
 */
@Serializable
data class DiscussionStart(
    @SerialName("comment")
    var comment: String,
    @SerialName("subject")
    var subject: DiscussionSubject,
    @SerialName("title")
    var title: String,
    /*@SerialName("extras")
    var extras: Map<String, Any?>? = null*/
)
