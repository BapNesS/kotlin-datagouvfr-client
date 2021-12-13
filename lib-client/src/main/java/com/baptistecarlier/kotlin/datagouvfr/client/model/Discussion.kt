package com.baptistecarlier.kotlin.datagouvfr.client.model

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property `class` The object class
 * @property closed The discussion closing date
 * @property closedBy The user who closed the discussion
 * @property created The discussion creation date
 * @property discussion
 * @property extras Extra attributes as key-value pairs
 * @property id The discussion identifier
 * @property subject The discussion target object
 * @property title The discussion title
 * @property url The discussion API URI
 * @property user The discussion author
 */
@MissingFieldMapping
@Serializable
data class Discussion(
    @SerialName("class")
    var `class`: String,
    @SerialName("closed")
    var closed: LocalDateTime? = null,
    /*@SerialName("closed_by")
    var closedBy: Map<String, Any?>? = null,*/
    @SerialName("created")
    var created: LocalDateTime? = null,
    @SerialName("discussion")
    var discussion: DiscussionMessage? = null,
    /*@SerialName("extras")
    var extras: Map<String, Any?>? = null,*/
    @SerialName("id")
    var id: String? = null,
    @SerialName("subject")
    var subject: DiscussionSubject? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("user")
    var user: User? = null
)
