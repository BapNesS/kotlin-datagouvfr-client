package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class DatasetsApiImpl(private val client: HttpClient) : DatasetsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    override fun getListDatasets(
        q: String?,
        /*facets: List<String>?,*/
        tag: String?,
        badge: String?,
        organization: String?,
        owner: String?,
        license: String?,
        geozone: String?,
        granularity: String?,
        format: String?,
        schema: String?,
        schemaVersion: String?,
        resourceType: String?,
        reuses: String?,
        temporalCoverage: String?,
        featured: Boolean?,
        sort: String?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<DatasetPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        /*builder.appendIfNotNull("facets", facets)*/
        builder.appendIfNotNull("tag", tag)
        builder.appendIfNotNull("badge", badge)
        builder.appendIfNotNull("organization", organization)
        builder.appendIfNotNull("owner", owner)
        builder.appendIfNotNull("license", license)
        builder.appendIfNotNull("geozone", geozone)
        builder.appendIfNotNull("granularity", granularity)
        builder.appendIfNotNull("format", format)
        builder.appendIfNotNull("schema", schema)
        builder.appendIfNotNull("schema_version", schemaVersion)
        builder.appendIfNotNull("resource_type", resourceType)
        builder.appendIfNotNull("reuses", reuses)
        builder.appendIfNotNull("temporal_coverage", temporalCoverage)
        builder.appendIfNotNull("featured", featured)
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "datasets/?${builder.urlEncore()}"
        )
    }

    override fun postCreateDataset(payload: Dataset): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.post(
            path = "datasets/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getAvailableDatasetBadges(): Flow<DgfrResource<Map<String, String>>> = loadingFlow {
        client.get(
            path = "datasets/badges/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListCommunityResources(
        sort: String?,
        page: Int?,
        pageSize: Int?,
        organization: String?,
        dataset: String?,
        owner: String?
    ): Flow<DgfrResource<CommunityResourcePage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)
        builder.appendIfNotNull("organization", organization)
        builder.appendIfNotNull("dataset", dataset)
        builder.appendIfNotNull("owner", owner)

        client.get(
            path = "datasets/community_resources/?${builder.urlEncore()}"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateCommunityResource(payload: CommunityResource): Flow<DgfrResource<CommunityResource>> = loadingFlow {
        client.post(
            path = "datasets/community_resources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun deleteCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrResource<CommunityResource>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        client.delete(
            path = "datasets/community_resources/$community/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getRetrieveCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrResource<CommunityResource>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        client.get(
            path = "datasets/community_resources/$community/?${builder.urlEncore()}"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateCommunityResource(
        community: String,
        payload: CommunityResource,
        dataset: String?
    ): Flow<DgfrResource<CommunityResource>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        client.put(
            path = "datasets/community_resources/$community/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postUploadCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrResource<UploadedResource>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        client.post(
            path = "datasets/community_resources/$community/upload/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun getAllowedExtensions(): Flow<DgfrResource<List<String>>> = loadingFlow {
        client.get(
            path = "datasets/extensions/"
        )
    }

    override fun getListFrequencies(): Flow<DgfrResource<List<Frequency>>> = loadingFlow {
        client.get(
            path = "datasets/frequencies/"
        )
    }

    override fun getListLicenses(): Flow<DgfrResource<List<License>>> = loadingFlow {
        client.get(
            path = "datasets/licenses/"
        )
    }

    override fun getRedirectResource(id: String, dataset: String?): Flow<DgfrResource<String>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        val response = client.get<HttpResponse>(
            path = "datasets/r/$id/?${builder.urlEncore()}"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getResourceTypes(): Flow<DgfrResource<List<ResourceType>>> = loadingFlow {
        client.get(
            path = "datasets/resource_types/"
        )
    }

    override fun getSchemas(): Flow<DgfrResource<List<Schema>>> = loadingFlow {
        client.get(
            path = "datasets/schemas/"
        )
    }

    override fun getSuggestDatasets(q: String, size: Int?): Flow<DgfrResource<List<DatasetSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "datasets/suggest/?${builder.urlEncore()}"
        )
    }

    override fun getSuggestFormats(q: String, size: Int?): Flow<DgfrResource<List<Format>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "datasets/suggest/formats/?${builder.urlEncore()}"
        )
    }

    override fun getSuggestMime(q: String, size: Int?): Flow<DgfrResource<List<Mime>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "datasets/suggest/mime/?${builder.urlEncore()}"
        )
    }

    override fun deleteDataset(dataset: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getDataset(dataset: String): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/"
        )
    }

    override fun putUpdateDataset(dataset: String, payload: Dataset): Flow<DgfrResource<Dataset>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        client.put(
            path = "datasets/$dataset/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun postAddDatasetBadge(dataset: String, payload: Badge): Flow<DgfrResource<Badge>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/badges/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteDatasetBadge(badgeKind: String, dataset: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun deleteUnfeatureDataset(dataset: String): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.delete(
            path = "datasets/$dataset/featured/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postFeatureDataset(dataset: String): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/featured/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
    }

    override fun getRdfDataset(dataset: String): Flow<DgfrResource<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "datasets/$dataset/rdf"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getRdfDatasetFormat(dataset: String, format: String): Flow<DgfrResource<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "datasets/$dataset/rdf.$format"
        )
        response.content.readAndClose().orEmpty()
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateResource(dataset: String, payload: Resource): Flow<DgfrResource<Resource>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/resources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateResources(
        dataset: String,
        payload: List<Resource>
    ): Flow<DgfrResource<List<Resource>>> = loadingFlow {
        client.put(
            path = "datasets/$dataset/resources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteResource(rid: String, dataset: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/resources/$rid/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getResource(rid: String, dataset: String): Flow<DgfrResource<Resource>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/resources/$rid/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateResource(
        rid: String,
        dataset: String,
        payload: Resource
    ): Flow<DgfrResource<Resource>> = loadingFlow {
        client.put(
            path = "datasets/$dataset/resources/$rid/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getCheckDatasetResource(rid: String, dataset: String): Flow<DgfrResource<Map<String, String>>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/resources/$rid/check/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postUploadDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrResource<UploadedResource>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/resources/$rid/upload/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postUploadNewDatasetResource(
        dataset: String,
        file: ByteArray,
        fileName: String,
        contentType: String,
        uuid: String?,
        partIndex: Int?,
        partByteOffset: Int?,
        totalParts: Int?,
        chunkSize: Int?
    ): Flow<DgfrResource<UploadedResource?>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "datasets/$dataset/upload/",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
                fbAppendIfNotNull("uuid", uuid)
                fbAppendIfNotNull("partindex", partIndex)
                fbAppendIfNotNull("partbyteoffset", partByteOffset)
                fbAppendIfNotNull("totalparts", totalParts)
                fbAppendIfNotNull("chunksize", chunkSize)
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

    /**
     * Upload a new community resource
     * @param dataset The dataset ID or slug (required)
     * @param file (optional)
     * @param uuid (optional)
     * @param filename (optional)
     * @param partindex (optional)
     * @param partbyteoffset (optional)
     * @param totalparts (optional)
     * @param chunksize (optional)
     */
    @OptIn(MissingFieldMapping::class)
    override fun postUploadNewCommunityResource(
        dataset: String,
        file: ByteArray,
        fileName: String,
        contentType: String,
        uuid: String?,
        partIndex: Int?,
        partByteOffset: Int?,
        totalParts: Int?,
        chunkSize: Int?
    ): Flow<DgfrResource<UploadedResource?>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "datasets/$dataset/upload/community/",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
                fbAppendIfNotNull("uuid", uuid)
                fbAppendIfNotNull("partindex", partIndex)
                fbAppendIfNotNull("partbyteoffset", partByteOffset)
                fbAppendIfNotNull("totalparts", totalParts)
                fbAppendIfNotNull("chunksize", chunkSize)
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

    override fun deleteUnfollowDataset(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListDatasetFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<FollowPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "datasets/$id/followers/?${builder.urlEncore()}"
        )
    }

    override fun postFollowDataset(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "datasets/$id/followers/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
        response.status.value in HttpCodeRangeSuccess
    }
}
