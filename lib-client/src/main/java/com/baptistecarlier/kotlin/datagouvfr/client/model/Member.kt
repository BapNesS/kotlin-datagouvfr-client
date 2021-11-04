package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property role The member role in the organization
 * @property user
 */
@Serializable
data class Member(
    @SerialName("role")
    var role: Member.RoleEnum,
    @SerialName("user")
    var user: UserReference? = null
) {
    /**
     * The member role in the organization
     * Values: ADMIN, EDITOR
     */
    @Serializable
    enum class RoleEnum(val value: String) {
        @SerialName("admin") ADMIN("admin"),
        @SerialName("editor") EDITOR("editor")
    }
}
