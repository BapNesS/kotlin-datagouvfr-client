package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property active Is this source active
 * @property autoarchive If enabled, datasets not present on the remote source will be automatically archived
 * @property backend The source backend
 * @property config The configuration as key-value pairs
 * @property createdAt The source creation date
 * @property deleted The source deletion date
 * @property description The source description
 * @property id The source unique identifier
 * @property lastJob The last job for this source
 * @property name The source display name
 * @property organization The producer organization
 * @property owner The owner information
 * @property schedule The source schedule (interval or cron expression)
 * @property url The source base URL
 * @property validation Has the source been validated
 */
@Serializable
data class HarvestSource(
    @SerialName("active")
    var active: Boolean,
    @SerialName("autoarchive")
    var autoarchive: Boolean,
    @SerialName("backend")
    var backend: HarvestSource.BackendEnum,
    @SerialName("created_at")
    var createdAt: LocalDateTime,
    @SerialName("name")
    var name: String,
    @SerialName("url")
    var url: String,
/*    @SerialName("config")
    var config: Map<String, Any?>? = null,*/
    @SerialName("deleted")
    var deleted: LocalDateTime? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("id")
    var id: String? = null,
/*    @SerialName("last_job")
    var lastJob: Map<String, Any?>? = null,*/
/*    @SerialName("organization")
    var organization: Map<String, Any?>? = null,*/
    @SerialName("owner")
    var owner: Owner? = null,
    @SerialName("schedule")
    var schedule: String? = null,
/*    @SerialName("validation")
    var validation: Map<String, Any?>? = null*/
) {
    /**
     * The source backend
     * Values: DCAT, ODS, MAAF, CKAN, DKAN
     */
    @Serializable
    enum class BackendEnum(val value: String) {
        @SerialName("dcat") DCAT("dcat"),
        @SerialName("ods") ODS("ods"),
        @SerialName("maaf") MAAF("maaf"),
        @SerialName("ckan") CKAN("ckan"),
        @SerialName("dkan") DKAN("dkan")
    }
}