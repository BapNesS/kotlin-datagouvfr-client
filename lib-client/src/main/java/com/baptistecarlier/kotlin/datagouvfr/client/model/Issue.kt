package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property `class` The object class
 * @property closed The issue closing date
 * @property closedBy The user who closed the issue
 * @property created The issue creation date
 * @property discussion
 * @property id The issue identifier
 * @property subject The issue target object
 * @property title The issue title
 * @property url The issue API URI
 * @property user The issue author
 */
@Serializable
data class Issue(
    @SerialName("class")
    var `class`: String,
    /*@SerialName("subject")
    var subject: Map<String, Any?>,*/
    @SerialName("title")
    var title: String,
    /*@SerialName("user")
    var user: Map<String, Any?>,*/
    @SerialName("closed")
    var closed: LocalDateTime? = null,
    @SerialName("closed_by")
    var closedBy: String? = null,
    @SerialName("created")
    var created: LocalDateTime? = null,
    @SerialName("discussion")
    var discussion: IssueMessage? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("url")
    var url: String? = null
)
