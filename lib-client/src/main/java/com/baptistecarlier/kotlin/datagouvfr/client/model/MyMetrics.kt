package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property datasetsCount The user&#39;s datasets number
 * @property datasetsOrgCount The user&#39;s orgs datasets number
 * @property followersCount The user&#39;s followers number
 * @property followersOrgCount The user&#39;s orgs followers number
 * @property id The user identifier
 * @property resourcesAvailability The user&#39;s resources availability percentage
 */
@Serializable
data class MyMetrics(
    @SerialName("id")
    var id: String,
    @SerialName("datasets_count")
    var datasetsCount: Int? = null,
    @SerialName("datasets_org_count")
    var datasetsOrgCount: Int? = null,
    @SerialName("followers_count")
    var followersCount: Int? = null,
    @SerialName("followers_org_count")
    var followersOrgCount: Int? = null,
    @SerialName("resources_availability")
    var resourcesAvailability: Long? = null
)
