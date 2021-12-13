package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

interface DatasetsApi : WithApiKey {

    /**
     * List or search all datasets
     * @param q The search query (optional)
     * @param facets Selected facets to fetch (optional)
     * @param tag (optional)
     * @param badge (optional)
     * @param organization (optional)
     * @param owner (optional)
     * @param license (optional)
     * @param geozone (optional)
     * @param granularity (optional)
     * @param format (optional)
     * @param schema (optional)
     * @param schemaVersion (optional)
     * @param resourceType (optional)
     * @param reuses (optional)
     * @param temporalCoverage Une couverture temporelle est exprimée par début-fin où les deux dates sont au format ISO (c&#39;est-à-dire YYYY-MM-DD-YYYY-MM-DD) (optional)
     * @param featured (optional)
     * @param sort The field (and direction) on which sorting apply (optional)
     * @param page The page to display (optional, default to 0)
     * @param pageSize The page size (optional, default
     */
    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    fun getListDatasets(
        q: String? = null,
        /*facets: List<String>? = null,*/
        tag: String? = null,
        badge: String? = null,
        organization: String? = null,
        owner: String? = null,
        license: String? = null,
        geozone: String? = null,
        granularity: String? = null,
        format: String? = null,
        schema: String? = null,
        schemaVersion: String? = null,
        resourceType: String? = null,
        reuses: String? = null,
        temporalCoverage: String? = null,
        featured: Boolean? = null,
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrResource<DatasetPage>>

    /**
     * Create a new dataset
     * @param payload (required)
     */
    fun postCreateDataset(
        payload: Dataset
    ): Flow<DgfrResource<Dataset>>

    /**
     * List all available dataset badges and their labels
     */
    fun getAvailableDatasetBadges(): Flow<DgfrResource<Map<String, String>>>

    /**
     * List all community resources
     * @param sort The sorting attribute (optional, default to -created)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param organization Filter activities for that particular organization (optional)
     * @param dataset Filter activities for that particular dataset (optional)
     * @param owner Filter activities for that particula
     */
    @OptIn(MissingFieldMapping::class)
    fun getListCommunityResources(
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null,
        organization: String? = null,
        dataset: String? = null,
        owner: String? = null
    ): Flow<DgfrResource<CommunityResourcePage>>

    /**
     * Create a new community resource
     * @param payload (required)
     */
    fun postCreateCommunityResource(
        payload: CommunityResource
    ): Flow<DgfrResource<CommunityResource>>

    /**
     * Delete a given community resource
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    fun deleteCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<DgfrResource<CommunityResource>>

    /**
     * Retrieve a community resource given its identifier
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    fun getRetrieveCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<DgfrResource<CommunityResource>>

    /**
     * Update a given community resource
     * @param community The community resource unique identifier (required)
     * @param payload (required)
     * @param dataset The dataset ID or slug (optional)
     */
    fun putUpdateCommunityResource(
        community: String,
        payload: CommunityResource,
        dataset: String? = null
    ): Flow<DgfrResource<CommunityResource>>

    /**
     * Update the file related to a given community resource
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    fun postUploadCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<DgfrResource<UploadedResource>>

    /**
     * List all allowed resources extensions
     */
    fun getAllowedExtensions(): Flow<DgfrResource<List<String>>>

    /**
     * List all available frequencies
     */
    fun getListFrequencies(): Flow<DgfrResource<List<Frequency>>>

    /**
     * List all available licenses
     */
    fun getListLicenses(): Flow<DgfrResource<List<License>>>

    /**
     * Redirect to the latest version of a resource given its
     * @param id (required)
     * @param dataset The dataset ID or slug (optional)
     */
    fun getRedirectResource(
        id: String,
        dataset: String?
    ): Flow<DgfrResource<String>>

    /**
     * List all resource types
     */
    fun getResourceTypes(): Flow<DgfrResource<List<ResourceType>>>

    /**
     * List all available schemas
     */
    fun getSchemas(): Flow<DgfrResource<List<Schema>>>

    /**
     * Suggest datasets
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (op
     */
    fun getSuggestDatasets(
        q: String,
        size: Int? = null
    ): Flow<DgfrResource<List<DatasetSuggestion>>>

    /**
     * Suggest file formats
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestFormats(
        q: String,
        size: Int?
    ): Flow<DgfrResource<List<Format>>>

    /**
     * Suggest mime types
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestMime(
        q: String,
        size: Int?
    ): Flow<DgfrResource<List<Mime>>>

    /**
     * Delete a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteDataset(
        dataset: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Get a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     */
    fun getDataset(
        dataset: String
    ): Flow<DgfrResource<Dataset>>

    /**
     * Update a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateDataset(
        dataset: String,
        payload: Dataset
    ): Flow<DgfrResource<Dataset>>

    /**
     * Create a new badge for a given dataset
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun postAddDatasetBadge(
        dataset: String,
        payload: Badge
    ): Flow<DgfrResource<Badge>>

    /**
     * Delete a badge for a given dataset
     * @param badgeKind (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteDatasetBadge(
        badgeKind: String,
        dataset: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Unmark the dataset as featured
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteUnfeatureDataset(
        dataset: String
    ): Flow<DgfrResource<Dataset>>

    /**
     * Mark the dataset as featured
     * @param dataset The dataset ID or slug (required)
     */
    fun postFeatureDataset(
        dataset: String
    ): Flow<DgfrResource<Dataset>>

    /**
     * @param dataset The dataset ID or slug (required)
     */
    fun getRdfDataset(
        dataset: String
    ): Flow<DgfrResource<String>>

    /**
     * @param dataset The dataset ID or slug (required)
     * @param format (required)
     */
    fun getRdfDatasetFormat(
        dataset: String,
        format: String
    ): Flow<DgfrResource<String>>

    /**
     * Create a new resource for a given dataset
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun postCreateResource(
        dataset: String,
        payload: Resource
    ): Flow<DgfrResource<Resource>>

    /**
     * Reorder resources
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateResources(
        dataset: String,
        payload: List<Resource>
    ): Flow<DgfrResource<List<Resource>>>

    /**
     * Delete a given resource on a given dataset
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteResource(
        rid: String,
        dataset: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Get a resource given its identifier
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun getResource(
        rid: String,
        dataset: String
    ): Flow<DgfrResource<Resource>>

    /**
     * Update a given resource on a given dataset
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateResource(
        rid: String,
        dataset: String,
        payload: Resource
    ): Flow<DgfrResource<Resource>>

    /**
     * Checks that a resource's URL exists and returns metadat
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun getCheckDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrResource<Map<String, String>>>

    /**
     * Upload a file related to a given resource on a given da
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun postUploadDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrResource<UploadedResource>>

    /**
     * Upload a new dataset resource
     * @param dataset The dataset ID or slug (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     * @param uuid (optional)
     * @param partindex (optional)
     * @param partbyteoffset (optional)
     * @param totalparts (optional)
     * @param chunksize (optional)
     */
    fun postUploadNewDatasetResource(
        dataset: String,
        file: ByteArray,
        fileName: String,
        contentType: String,
        uuid: String? = null,
        partIndex: Int? = null,
        partByteOffset: Int? = null,
        totalParts: Int? = null,
        chunkSize: Int? = null
    ): Flow<DgfrResource<UploadedResource?>>

    /**
     * Upload a new community resource
     * @param dataset The dataset ID or slug (required)
     * @param file (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     * @param uuid (optional)
     * @param partindex (optional)
     * @param partbyteoffset (optional)
     * @param totalparts (optional)
     * @param chunksize (optional)
     */
    fun postUploadNewCommunityResource(
        dataset: String,
        file: ByteArray,
        fileName: String,
        contentType: String,
        uuid: String? = null,
        partIndex: Int? = null,
        partByteOffset: Int? = null,
        totalParts: Int? = null,
        chunkSize: Int? = null
    ): Flow<DgfrResource<UploadedResource>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operatio
     * @param id (required)
     */
    fun deleteUnfollowDataset(
        id: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional,
     */
    @OptIn(MissingFieldMapping::class)
    fun getListDatasetFollowers(
        id: String,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrResource<FollowPage>>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operatio
     * @param id (required)
     */
    fun postFollowDataset(
        id: String
    ): Flow<DgfrResource<Boolean>>
}
