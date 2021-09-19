package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property actor The user who performed the action
 * @property createdAt When the action has been performed
 * @property icon The icon of the activity
 * @property key The key of the activity
 * @property kwargs Some action specific context
 * @property label The label of the activity
 * @property organization The organization who performed the action
 * @property relatedTo The activity target name
 * @property relatedToId The activity target object identifier
 * @property relatedToKind The activity target object class name
 * @property relatedToUrl The activity target model
 */
@Serializable
data class Activity(
    @SerialName("icon")
    var icon: String,
    @SerialName("key")
    var key: String,
    @SerialName("label")
    var label: String,
    @SerialName("related_to")
    var relatedTo: String,
    @SerialName("related_to_id")
    var relatedToId: String,
    @SerialName("related_to_kind")
    var relatedToKind: String,
    @SerialName("related_to_url")
    var relatedToUrl: String,
    /*@SerialName("actor")
    var actor: Map<String, Any?>? = null,*/
    @SerialName("created_at")
    var createdAt: LocalDateTime,
    /*@SerialName("kwargs")
    var kwargs: Map<String, Any?>? = null,
    @SerialName("organization")
    var organization: Map<String, Any?>? = null*/
)