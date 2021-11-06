package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Resource type (documentation, API...)
 * Values: MAIN, DOCUMENTATION, UPDATE, API, CODE, OTHER
 */
@Serializable
enum class TypeEnum(val value: String) {
    @SerialName("main")
    MAIN("main"),
    @SerialName("documentation")
    DOCUMENTATION("documentation"),
    @SerialName("update")
    UPDATE("update"),
    @SerialName("api")
    API("api"),
    @SerialName("code")
    CODE("code"),
    @SerialName("other")
    OTHER("other"),
    @SerialName("application")
    APPLICATION("application"),
    @SerialName("idea")
    IDEA("idea"),
    @SerialName("news_article")
    NEWS_ARTICLE("news_article"),
    @SerialName("paper")
    PAPER("paper"),
    @SerialName("post")
    POST("post"),
    @SerialName("visualization")
    VISUALIZATION("visualization"),
    @SerialName("hardware")
    HARDWARE("hardware")
}
