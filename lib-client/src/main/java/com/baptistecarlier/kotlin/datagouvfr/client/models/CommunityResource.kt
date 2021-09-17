package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.acl.Owner

/**
 * @property checksum A checksum to validate file validity
 * @property createdAt The resource creation date
 * @property description The resource markdown description
 * @property extras Extra attributes as key-value pairs
 * @property filesize The resource file size in bytes
 * @property fileType Whether the resource is an uploaded file, a remote file or an API
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
 * @property dataset Reference to the associated dataset
 * @property organization The producer organization
 * @property owner The user information
 */
@Serializable
data class CommunityResource(
    @SerialName("filetype")
    var fileType: FileTypeEnum,
    @SerialName("format")
    var format: String,
    @SerialName("title")
    var title: String,
    @SerialName("type")
    var type: Type,
    @SerialName("url")
    var url: String,
    //@SerialName("checksum")
    //var checksum: Map<String, Any?>? = null,
    @SerialName("created_at")
    var createdAt: LocalDateTime? = null,
    @SerialName("description")
    var description: String? = null,
    //@SerialName("extras")
    //var extras: Map<String, Any?>? = null,
    @SerialName("filesize")
    var filesize: Long? = null,
    @SerialName("id")
    var id: String? = null,
    //@SerialName("last_modified")
    //var lastModified: LocalDateTime? = null,
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
    //@SerialName("schema")
    //var schema: Map<String, Any?>? = null,
    //@SerialName("dataset")
    //var dataset: Map<String, Any?>? = null,
    //@SerialName("organization")
    //var organization: Map<String, Any?>? = null,
    @SerialName("owner")
    var owner: Owner? = null
) {

    /**
     * Resource type (documentation, API...)
     * Values: MAIN, DOCUMENTATION, UPDATE, API, CODE, OTHER
     */
    @Serializable
    enum class Type {
        @SerialName("main")
        MAIN,
        @SerialName("documentation")
        DOCUMENTATION,
        @SerialName("update")
        UPDATE,
        @SerialName("api")
        API,
        @SerialName("code")
        CODE,
        @SerialName("other")
        OTHER
    }
}