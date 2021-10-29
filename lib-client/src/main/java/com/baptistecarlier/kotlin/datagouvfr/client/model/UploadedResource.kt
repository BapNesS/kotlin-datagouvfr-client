package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property checksum A checksum to validate file validity
 * @property createdAt The resource creation date
 * @property description The resource markdown description
 * @property extras Extra attributes as key-value pairs
 * @property filesize The resource file size in bytes
 * @property filetype Whether the resource is an uploaded file, a remote file or an API
 * @property format The resource format
 * @property id The resource unique ID
 * @property lastModified The resource last modification date
 * @property latest The permanent URL redirecting to the latest version of the resource. When the resource data is updated, the URL will change, the latest URL won&#39;t.
 * @property metrics The resource metrics
 * @property mime The resource mime type
 * @property previewUrl An optional preview URL to be loaded as a standalone page (ie. iframe or new page)
 * @property published The resource publication date
 * @property schema Reference to the associated schema
 * @property title The resource title
 * @property type Resource type (documentation, API...)
 * @property url The resource URL
 * @property success Whether the upload succeeded or not.
 */
@Serializable
data class UploadedResource(
    @SerialName("filetype")
    var filetype: UploadedResource.FiletypeEnum,
    @SerialName("format")
    var format: String,
    @SerialName("title")
    var title: String,
    @SerialName("type")
    var type: UploadedResource.TypeEnum,
    @SerialName("url")
    var url: String,
    /*@SerialName("checksum")
    var checksum: Map<String, Any?>? = null,*/
    @SerialName("created_at")
    var createdAt: LocalDateTime? = null,
    @SerialName("description")
    var description: String? = null,
    /*@SerialName("extras")
    var extras: Map<String, Any?>? = null,*/
    @SerialName("filesize")
    var filesize: Int? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("last_modified")
    var lastModified: LocalDateTime? = null,
    @SerialName("latest")
    var latest: String? = null,
    @SerialName("metrics")
    var metrics: Metrics? = null,
    @SerialName("mime")
    var mime: String? = null,
    @SerialName("preview_url")
    var previewUrl: String? = null,
    @SerialName("published")
    var published: LocalDateTime? = null,
    /*@SerialName("schema")
    var schema: Map<String, Any?>? = null,*/
    @SerialName("success")
    var success: Boolean? = null
) {
    /**
     * Whether the resource is an uploaded file, a remote file or an API
     * Values: FILE, REMOTE
     */
    @Serializable
    enum class FiletypeEnum(val value: String) {
        @SerialName("file") FILE("file"),
        @SerialName("remote") REMOTE("remote")
    }
    /**
     * Resource type (documentation, API...)
     * Values: MAIN, DOCUMENTATION, UPDATE, API, CODE, OTHER
     */
    @Serializable
    enum class TypeEnum(val value: String) {
        @SerialName("main") MAIN("main"),
        @SerialName("documentation") DOCUMENTATION("documentation"),
        @SerialName("update") UPDATE("update"),
        @SerialName("api") API("api"),
        @SerialName("code") CODE("code"),
        @SerialName("other") OTHER("other")
    }
}