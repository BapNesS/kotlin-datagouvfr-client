package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatasetsApiImpl(private val client: HttpClient) : DatasetsApi {

    private val tag = "DatasetApiImpl"

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
    ): Flow<DatasetPage?> = flow {
        Log.d(tag, "getListDatasets / begin")
        val value = try {
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

            val response = client.get<DatasetPage>(
                path = "datasets/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateDataset(payload: Dataset): Flow<Dataset?> = flow {
        Log.d(tag, "postCreateDataset / begin")
        val value = try {
            val response = client.post<Dataset>(
                path = "datasets/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getAvailableDatasetBadges(): Flow<Map<String, String>?> = flow {
        Log.d(tag, "getAvailableDatasetBadges / begin")
        val value = try {
            val response = client.get<Map<String, String>>(
                path = "datasets/badges/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getAvailableDatasetBadges / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListCommunityResources(
        sort: String?,
        page: Int?,
        pageSize: Int?,
        organization: String?,
        dataset: String?,
        owner: String?
    ): Flow<CommunityResourcePage?> = flow {
        Log.d(tag, "getListCommunityResources / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)
            builder.appendIfNotNull("organization", organization)
            builder.appendIfNotNull("dataset", dataset)
            builder.appendIfNotNull("owner", pageSize)

            val response = client.get<CommunityResourcePage>(
                path = "datasets/community_resources/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListCommunityResources / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateCommunityResource(payload: CommunityResource): Flow<CommunityResource?> = flow {
        Log.d(tag, "postCreateCommunityResource / begin")
        val value = try {
            val response = client.post<CommunityResource>(
                path = "datasets/community_resources/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateCommunityResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteCommunityResource(
        community: String,
        dataset: String?
    ): Flow<CommunityResource?> = flow {
        Log.d(tag, "deleteCommunityResource / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("dataset", dataset)

            val response = client.delete<CommunityResource>(
                path = "datasets/community_resources/$community/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "deleteCommunityResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getRetrieveCommunityResource(
        community: String,
        dataset: String?
    ): Flow<CommunityResource?> = flow {
        Log.d(tag, "getRetrieveCommunityResource / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("dataset", dataset)

            val response = client.get<CommunityResource>(
                path = "datasets/community_resources/$community/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getRetrieveCommunityResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateCommunityResource(
        community: String,
        payload: CommunityResource,
        dataset: String?
    ): Flow<CommunityResource?> = flow {
        Log.d(tag, "putUpdateCommunityResource / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("dataset", dataset)

            val response = client.put<CommunityResource>(
                path = "datasets/community_resources/$community/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateCommunityResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postUploadCommunityResource(
        community: String,
        dataset: String?
    ): Flow<UploadedResource?> = flow {
        Log.d(tag, "postUploadCommunityResource / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("dataset", dataset)

            val response = client.post<UploadedResource>(
                path = "datasets/community_resources/$community/upload/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postUploadCommunityResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getAllowedExtensions(): Flow<List<String>?> = flow {
        Log.d(tag, "getAllowedExtensions / begin")
        val value = try {
            val response = client.get<List<String>>(
                path = "datasets/extensions/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getAllowedExtensions / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListFrequencies(): Flow<List<Frequency>?> = flow {
        Log.d(tag, "getListFrequencies / begin")
        val value = try {
            val response = client.get<List<Frequency>>(
                path = "datasets/frequencies/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListFrequencies / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListLicenses(): Flow<List<License>?> = flow {
        Log.d(tag, "getListLicenses / begin")
        val value = try {
            val response = client.get<List<License>>(
                path = "datasets/licenses/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListLicenses / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getRedirectResource(id: String, dataset: String?): Flow<String?> = flow {
        Log.d(tag, "getRedirectResource / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("dataset", dataset)

            val response = client.get<HttpResponse?>(
                path = "datasets/r/$id/?${builder.urlEncore()}"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getRedirectResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getResourceTypes(): Flow<List<ResourceType>?> = flow {
        Log.d(tag, "getResourceTypes / begin")
        val value = try {
            val response = client.get<List<ResourceType>?>(
                path = "datasets/resource_types/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getResourceTypes / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSchemas(): Flow<List<Schema>?> = flow {
        Log.d(tag, "getSchemas / begin")
        val value = try {
            val response = client.get<List<Schema>?>(
                path = "datasets/schemas/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSchemas / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestDatasets(q: String, size: Int?): Flow<List<DatasetSuggestion>?> = flow {
        Log.d(tag, "getSuggestDatasets / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<DatasetSuggestion>?>(
                path = "datasets/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestFormats(q: String, size: Int?): Flow<List<Format>?> = flow {
        Log.d(tag, "getSuggestFormats / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<Format>?>(
                path = "datasets/suggest/formats/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestFormats / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestMime(q: String, size: Int?): Flow<List<Mime>?> = flow {
        Log.d(tag, "getSuggestMime / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<Mime>?>(
                path = "datasets/suggest/mime/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestMime / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteDataset(dataset: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteDataset / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "datasets/$dataset/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getDataset(dataset: String): Flow<Dataset?> = flow {
        Log.d(tag, "getDataset / begin")
        val value = try {
            val response = client.get<Dataset>(
                path = "datasets/$dataset/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateDataset(dataset: String, payload: Dataset): Flow<Dataset?> = flow {
        Log.d(tag, "putUpdateDataset / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("dataset", dataset)

            val response = client.put<Dataset>(
                path = "datasets/$dataset/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postAddDatasetBadge(dataset: String, payload: Badge): Flow<Badge?> = flow {
        Log.d(tag, "postAddDatasetBadge / begin")
        val value = try {
            val response = client.post<Badge>(
                path = "datasets/$dataset/badges/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postAddDatasetBadge / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteDatasetBadge(badgeKind: String, dataset: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteDatasetBadge / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "datasets/$dataset/badges/$badgeKind/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteDatasetBadge / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnfeatureDataset(dataset: String): Flow<Dataset?> = flow {
        Log.d(tag, "deleteUnfollowDataset / begin")
        val value = try {
            val response = client.delete<Dataset>(
                path = "datasets/$dataset/featured/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "deleteUnfollowDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postFeatureDataset(dataset: String): Flow<Dataset?> = flow {
        Log.d(tag, "postFeatureDataset / begin")
        val value = try {
            val response = client.post<Dataset>(
                path = "datasets/$dataset/featured/"
            ) {
                addApiKey(apiKey)
                // Post without payload ?
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postFeatureDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getRdfDataset(dataset: String): Flow<String?> = flow {
        Log.d(tag, "getRdfDataset / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "datasets/$dataset/rdf"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getRdfDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getRdfDatasetFormat(dataset: String, format: String): Flow<String?> = flow {
        Log.d(tag, "getRdfDatasetFormat / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "datasets/$dataset/rdf.$format"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getRdfDatasetFormat / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateResource(dataset: String, payload: Resource): Flow<Resource?> = flow {
        Log.d(tag, "postCreateResource / begin")
        val value = try {
            val response = client.post<Resource>(
                path = "datasets/$dataset/resources/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateResources(
        dataset: String,
        payload: List<Resource>
    ): Flow<List<Resource>?> = flow {
        Log.d(tag, "putUpdateResources / begin")
        val value = try {
            val response = client.put<List<Resource>>(
                path = "datasets/$dataset/resources/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateResources / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteResource(rid: String, dataset: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteResource / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "datasets/$dataset/resources/$rid/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getResource(rid: String, dataset: String): Flow<Resource?> = flow {
        Log.d(tag, "getResource / begin")
        val value = try {
            val response = client.get<Resource>(
                path = "datasets/$dataset/resources/$rid/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateResource(
        rid: String,
        dataset: String,
        payload: Resource
    ): Flow<Resource?> = flow {
        Log.d(tag, "putUpdateResource / begin")
        val value = try {
            val response = client.put<Resource>(
                path = "datasets/$dataset/resources/$rid/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getCheckDatasetResource(rid: String, dataset: String): Flow<Map<String, String>?> = flow {
        Log.d(tag, "getCheckDatasetResource / begin")
        val value = try {
            val response = client.get<Map<String, String>>(
                path = "datasets/$dataset/resources/$rid/check/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getCheckDatasetResource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postUploadDatasetResource(
        rid: String,
        dataset: String
    ): Flow<UploadedResource?> = flow {
        Log.d(tag, "postUploadDatasetResource / begin")
        val value = try {
            val response = client.post<UploadedResource>(
                path = "datasets/$dataset/resources/$rid/upload/"
            ) {
                addApiKey(apiKey)
                // Post without payload ?
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postUploadDatasetResource / Exception =  $e")
            null
        }
        emit(value)
    }

    /*override suspend fun postUploadNewDatasetResource(
        dataset: String,
        file: RequestBody?,
        uuid: String?,
        filename: String?,
        partindex: Int?,
        partbyteoffset: Int?,
        totalparts: Int?,
        chunksize: Int?
    ): Flow<UploadedResource?> {
        TODO("Not yet implemented")
    }

    override suspend fun postUploadNewCommunityResource(
        dataset: String,
        file: RequestBody?,
        uuid: String?,
        filename: String?,
        partindex: Int?,
        partbyteoffset: Int?,
        totalparts: Int?,
        chunksize: Int?
    ): Flow<UploadedResource?> {
        TODO("Not yet implemented")
    }*/

    override suspend fun deleteUnfollowDataset(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteUnfollowDataset / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "datasets/$id/followers/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteUnfollowDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListDatasetFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<FollowPage?> = flow {
        Log.d(tag, "getListDatasetFollowers / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<FollowPage>(
                path = "datasets/$id/followers/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListDatasetFollowers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postFollowDataset(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "postFollowDataset / begin")
        val value = try {
            val response = client.post<HttpResponse>(
                path = "datasets/$id/followers/"
            ) {
                addApiKey(apiKey)
                // Post without payload ?
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "postFollowDataset / Exception =  $e")
            null
        }
        emit(value)
    }

}