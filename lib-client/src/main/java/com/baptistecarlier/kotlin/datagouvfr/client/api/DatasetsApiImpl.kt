package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class DatasetsApiImpl(private val client: HttpClient) : DatasetsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListDatasets(
        q: String?,
        facets: List<String>?,
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
        builder.appendIfNotNull("facets", facets)
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

    override suspend fun postCreateDataset(payload: Dataset): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.post(
            path = "datasets/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun getAvailableDatasetBadges(): Flow<DgfrResource<Map<String, String>>> = loadingFlow {
        client.get(
            path = "datasets/badges/"
        )
    }

    override suspend fun getListCommunityResources(
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

    override suspend fun postCreateCommunityResource(payload: CommunityResource): Flow<DgfrResource<CommunityResource>> = loadingFlow {
        client.post(
            path = "datasets/community_resources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteCommunityResource(
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

    override suspend fun getRetrieveCommunityResource(
        community: String,
        dataset: String?
    ): Flow<DgfrResource<CommunityResource>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        client.get(
            path = "datasets/community_resources/$community/?${builder.urlEncore()}"
        )
    }

    override suspend fun putUpdateCommunityResource(
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

    override suspend fun postUploadCommunityResource(
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

    override suspend fun getAllowedExtensions(): Flow<DgfrResource<List<String>>> = loadingFlow {
        client.get(
            path = "datasets/extensions/"
        )
    }

    override suspend fun getListFrequencies(): Flow<DgfrResource<List<Frequency>>> = loadingFlow {
        client.get(
            path = "datasets/frequencies/"
        )
    }

    override suspend fun getListLicenses(): Flow<DgfrResource<List<License>>> = loadingFlow {
        client.get(
            path = "datasets/licenses/"
        )
    }

    override suspend fun getRedirectResource(id: String, dataset: String?): Flow<DgfrResource<String>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("dataset", dataset)

        val response = client.get<HttpResponse>(
            path = "datasets/r/$id/?${builder.urlEncore()}"
        )
        response.content.readAndClose().orEmpty()
    }

    override suspend fun getResourceTypes(): Flow<DgfrResource<List<ResourceType>>> = loadingFlow {
        client.get(
            path = "datasets/resource_types/"
        )
    }

    override suspend fun getSchemas(): Flow<DgfrResource<List<Schema>>> = loadingFlow {
        client.get(
            path = "datasets/schemas/"
        )
    }

    override suspend fun getSuggestDatasets(q: String, size: Int?): Flow<DgfrResource<List<DatasetSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "datasets/suggest/?${builder.urlEncore()}"
        )
    }

    override suspend fun getSuggestFormats(q: String, size: Int?): Flow<DgfrResource<List<Format>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "datasets/suggest/formats/?${builder.urlEncore()}"
        )
    }

    override suspend fun getSuggestMime(q: String, size: Int?): Flow<DgfrResource<List<Mime>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "datasets/suggest/mime/?${builder.urlEncore()}"
        )
    }

    override suspend fun deleteDataset(dataset: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getDataset(dataset: String): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/"
        )
    }

    override suspend fun putUpdateDataset(dataset: String, payload: Dataset): Flow<DgfrResource<Dataset>> = loadingFlow {
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

    override suspend fun postAddDatasetBadge(dataset: String, payload: Badge): Flow<DgfrResource<Badge>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/badges/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteDatasetBadge(badgeKind: String, dataset: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun deleteUnfeatureDataset(dataset: String): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.delete(
            path = "datasets/$dataset/featured/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun postFeatureDataset(dataset: String): Flow<DgfrResource<Dataset>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/featured/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
    }

    override suspend fun getRdfDataset(dataset: String): Flow<DgfrResource<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "datasets/$dataset/rdf"
        )
         response.content.readAndClose().orEmpty()
    }

    override suspend fun getRdfDatasetFormat(dataset: String, format: String): Flow<DgfrResource<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "datasets/$dataset/rdf.$format"
        )
         response.content.readAndClose().orEmpty()
    }

    override suspend fun postCreateResource(dataset: String, payload: Resource): Flow<DgfrResource<Resource>> = loadingFlow {
        client.post(
            path = "datasets/$dataset/resources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun putUpdateResources(
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

    override suspend fun deleteResource(rid: String, dataset: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$dataset/resources/$rid/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getResource(rid: String, dataset: String): Flow<DgfrResource<Resource>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/resources/$rid/"
        )
    }

    override suspend fun putUpdateResource(
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

    override suspend fun getCheckDatasetResource(rid: String, dataset: String): Flow<DgfrResource<Map<String, String>>> = loadingFlow {
        client.get(
            path = "datasets/$dataset/resources/$rid/check/"
        )
    }

    override suspend fun postUploadDatasetResource(
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

    override suspend fun postUploadNewDatasetResource(
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
                append("file", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    append(HttpHeaders.ContentType, contentType)
                })
                fbAppendIfNotNull("uuid", uuid)
                fbAppendIfNotNull("partindex", partIndex)
                fbAppendIfNotNull("partbyteoffset", partByteOffset)
                fbAppendIfNotNull("totalparts", totalParts)
                fbAppendIfNotNull("chunksize", chunkSize)
            }
        ) {
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
    override suspend fun postUploadNewCommunityResource(
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
                append("file", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    append(HttpHeaders.ContentType, contentType)
                })
                fbAppendIfNotNull("uuid", uuid)
                fbAppendIfNotNull("partindex", partIndex)
                fbAppendIfNotNull("partbyteoffset", partByteOffset)
                fbAppendIfNotNull("totalparts", totalParts)
                fbAppendIfNotNull("chunksize", chunkSize)
            }
        ) {
            header("X-API-KEY", apiKey)
        }
    }

    override suspend fun deleteUnfollowDataset(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "datasets/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getListDatasetFollowers(
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

    override suspend fun postFollowDataset(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "datasets/$id/followers/"
        ) {
            addApiKey(apiKey)
            // Post without payload ?
        }
        (response.status.value in HttpCodeRangeSucces)
    }

}