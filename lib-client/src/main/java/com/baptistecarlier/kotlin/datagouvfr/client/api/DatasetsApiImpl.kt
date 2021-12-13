package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
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
    ): Flow<DgfrCallState<DatasetPage>> = loadingFlow {
        client.get(
            path = "datasets/"
        ) {
            parameter("q", q)
            // TODO Not sure about this
            facets?.forEach { item ->
                parameter("facets", item)
            }
            parameter("tag", tag)
            parameter("badge", badge)
            parameter("organization", organization)
            parameter("owner", owner)
            parameter("license", license)
            parameter("geozone", geozone)
            parameter("granularity", granularity)
            parameter("format", format)
            parameter("schema", schema)
            parameter("schema_version", schemaVersion)
            parameter("resource_type", resourceType)
            parameter("reuses", reuses)
            parameter("temporal_coverage", temporalCoverage)
            parameter("featured", featured)
            parameter("sort", sort)
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postCreateDataset(payload: Dataset): Flow<DgfrCallState<Dataset>> = loadingFlow {
        client.post(
            path = "datasets/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getAvailableDatasetBadges(): Flow<DgfrCallState<Map<String, String>>> =
        loadingFlow {
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
    ): Flow<DgfrCallState<CommunityResourcePage>> = loadingFlow {
        client.get(
            path = "datasets/community_resources/"
        ) {
            parameter("sort", sort)
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("organization", organization)
            parameter("dataset", dataset)
            parameter("owner", owner)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateCommunityResource(payload: CommunityResource): Flow<DgfrCallState<CommunityResource>> =
        loadingFlow {
            client.post(
                path = "datasets/community_resources/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
        }

    @OptIn(MissingFieldMapping::class)
    override fun getRetrieveCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrCallState<CommunityResource>> = loadingFlow {
        client.get(
            path = "datasets/community_resources/$community/"
        ) {
            parameter("dataset", dataset)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun deleteCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrCallState<CommunityResource>> = loadingFlow {
        client.delete(
            path = "datasets/community_resources/$community/"
        ) {
            addApiKey(apiKey)
            parameter("dataset", dataset)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateCommunityResource(
        community: String,
        payload: CommunityResource,
        dataset: String?
    ): Flow<DgfrCallState<CommunityResource>> = loadingFlow {
        client.put(
            path = "datasets/community_resources/$community/"
        ) {
            addApiKey(apiKey)
            parameter("dataset", dataset)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postUploadCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrCallState<UploadedResource>> = loadingFlow {
        client.post(
            path = "datasets/community_resources/$community/upload/"
        ) {
            addApiKey(apiKey)
            parameter("dataset", dataset)
        }
    }

    override fun getAllowedExtensions(): Flow<DgfrCallState<List<String>>> = loadingFlow {
        client.get(
            path = "datasets/extensions/"
        )
    }

    override fun getListFrequencies(): Flow<DgfrCallState<List<Frequency>>> = loadingFlow {
        client.get(
            path = "datasets/frequencies/"
        )
    }

    override fun getListLicenses(): Flow<DgfrCallState<List<License>>> = loadingFlow {
        client.get(
            path = "datasets/licenses/"
        )
    }

    override fun getRedirectResource(id: String, dataset: String?): Flow<DgfrCallState<String>> =
        loadingFlow {
            val response = client.get<HttpResponse>(
                path = "datasets/r/$id"
            ) {
                parameter("dataset", dataset)
            }
            response.content.readAndClose().orEmpty()
        }

    override fun getResourceTypes(): Flow<DgfrCallState<List<ResourceType>>> = loadingFlow {
        client.get(
            path = "datasets/resource_types/"
        )
    }

    override fun getSchemas(): Flow<DgfrCallState<List<Schema>>> = loadingFlow {
        client.get(
            path = "datasets/schemas/"
        )
    }

    override fun getSuggestDatasets(
        q: String,
        size: Int?
    ): Flow<DgfrCallState<List<DatasetSuggestion>>> = loadingFlow {
        client.get(
            path = "datasets/suggest/"
        ) {
            parameter("q", q)
            parameter("size", size)
        }
    }

    override fun getSuggestFormats(q: String, size: Int?): Flow<DgfrCallState<List<Format>>> =
        loadingFlow {
            client.get(
                path = "datasets/suggest/formats/"
            ) {
                parameter("q", q)
                parameter("size", size)
            }
        }

    override fun getSuggestMime(q: String, size: Int?): Flow<DgfrCallState<List<Mime>>> =
        loadingFlow {
            client.get(
                path = "datasets/suggest/mime/"
            ) {
                parameter("q", q)
                parameter("size", size)
            }
        }

    override fun deleteDataset(dataset: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getDataset(dataset: String): Flow<DgfrCallState<Dataset>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/"
        )
    }

    override fun putUpdateDataset(dataset: String, payload: Dataset): Flow<DgfrCallState<Dataset>> =
        loadingFlow {
            client.put(
                path = "datasets/$dataset/"
            ) {
                addApiKey(apiKey)
                parameter("dataset", dataset)
                contentType(ContentType.Application.Json)
                body = payload
            }
        }

    override fun postAddDatasetBadge(dataset: String, payload: Badge): Flow<DgfrCallState<Badge>> =
        loadingFlow {
            client.post(
                path = "datasets/$dataset/badges/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
        }

    override fun deleteDatasetBadge(
        badgeKind: String,
        dataset: String
    ): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun deleteUnfeatureDataset(dataset: String): Flow<DgfrCallState<Dataset>> =
        loadingFlow {
            client.delete(
                path = "datasets/$dataset/featured/"
            ) {
                addApiKey(apiKey)
            }
        }

    override fun postFeatureDataset(dataset: String): Flow<DgfrCallState<Dataset>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/featured/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
    }

    override fun getRdfDataset(dataset: String): Flow<DgfrCallState<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "datasets/$dataset/rdf"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getRdfDatasetFormat(dataset: String, format: String): Flow<DgfrCallState<String>> =
        loadingFlow {
            val response = client.get<HttpResponse>(
                path = "datasets/$dataset/rdf.$format"
            )
            response.content.readAndClose().orEmpty()
        }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateResource(
        dataset: String,
        payload: Resource
    ): Flow<DgfrCallState<Resource>> = loadingFlow {
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
    ): Flow<DgfrCallState<List<Resource>>> = loadingFlow {
        client.put(
            path = "datasets/$dataset/resources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteResource(rid: String, dataset: String): Flow<DgfrCallState<Boolean>> =
        loadingFlow {
            val response = client.delete<HttpResponse>(
                path = "datasets/$dataset/resources/$rid/"
            ) {
                addApiKey(apiKey)
            }
            response.status.value in HttpCodeRangeSuccess
        }

    @OptIn(MissingFieldMapping::class)
    override fun getResource(rid: String, dataset: String): Flow<DgfrCallState<Resource>> =
        loadingFlow {
            client.get(
                path = "datasets/$dataset/resources/$rid/"
            )
        }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateResource(
        rid: String,
        dataset: String,
        payload: Resource
    ): Flow<DgfrCallState<Resource>> = loadingFlow {
        client.put(
            path = "datasets/$dataset/resources/$rid/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getCheckDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrCallState<Map<String, String>>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/resources/$rid/check/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postUploadDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrCallState<UploadedResource>> = loadingFlow {
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
    ): Flow<DgfrCallState<UploadedResource?>> = loadingFlow {
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
    ): Flow<DgfrCallState<UploadedResource>> = loadingFlow {
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

    override fun deleteUnfollowDataset(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
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
    ): Flow<DgfrCallState<FollowPage>> = loadingFlow {
        client.get(
            path = "datasets/$id/followers/"
        ) {
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postFollowDataset(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "datasets/$id/followers/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
        response.status.value in HttpCodeRangeSuccess
    }
}
