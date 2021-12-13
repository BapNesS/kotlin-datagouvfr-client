package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

internal interface DatasetsApi : WithApiKey {

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
    fun getListDatasets(
        q: String? = null,
        facets: List<String>? = null,
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
    ): Flow<DgfrCallState<DatasetPage>>

    /**
     * Create a new dataset
     * @param payload (required)
     */
    fun postCreateDataset(
        payload: Dataset
    ): Flow<DgfrCallState<Dataset>>

    /**
     * List all available dataset badges and their labels
     */
    fun getAvailableDatasetBadges(): Flow<DgfrCallState<Map<String, String>>>

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
    ): Flow<DgfrCallState<CommunityResourcePage>>

    /**
     * Create a new community resource
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateCommunityResource(
        payload: CommunityResource
    ): Flow<DgfrCallState<CommunityResource>>

    /**
     * Delete a given community resource
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun deleteCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<DgfrCallState<CommunityResource>>

    /**
     * Retrieve a community resource given its identifier
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun getRetrieveCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<DgfrCallState<CommunityResource>>

    /**
     * Update a given community resource
     * @param community The community resource unique identifier (required)
     * @param payload (required)
     * @param dataset The dataset ID or slug (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun putUpdateCommunityResource(
        community: String,
        payload: CommunityResource,
        dataset: String? = null
    ): Flow<DgfrCallState<CommunityResource>>

    /**
     * Update the file related to a given community resource
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun postUploadCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<DgfrCallState<UploadedResource>>

    /**
     * List all allowed resources extensions
     */
    fun getAllowedExtensions(): Flow<DgfrCallState<List<String>>>

    /**
     * List all available frequencies
     */
    fun getListFrequencies(): Flow<DgfrCallState<List<Frequency>>>

    /**
     * List all available licenses
     */
    fun getListLicenses(): Flow<DgfrCallState<List<License>>>

    /**
     * Redirect to the latest version of a resource given its
     * @param id (required)
     * @param dataset The dataset ID or slug (optional)
     */
    fun getRedirectResource(
        id: String,
        dataset: String?
    ): Flow<DgfrCallState<String>>

    /**
     * List all resource types
     */
    fun getResourceTypes(): Flow<DgfrCallState<List<ResourceType>>>

    /**
     * List all available schemas
     */
    fun getSchemas(): Flow<DgfrCallState<List<Schema>>>

    /**
     * Suggest datasets
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (op
     */
    fun getSuggestDatasets(
        q: String,
        size: Int? = null
    ): Flow<DgfrCallState<List<DatasetSuggestion>>>

    /**
     * Suggest file formats
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestFormats(
        q: String,
        size: Int?
    ): Flow<DgfrCallState<List<Format>>>

    /**
     * Suggest mime types
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestMime(
        q: String,
        size: Int?
    ): Flow<DgfrCallState<List<Mime>>>

    /**
     * Delete a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteDataset(
        dataset: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Get a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     */
    fun getDataset(
        dataset: String
    ): Flow<DgfrCallState<Dataset>>

    /**
     * Update a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateDataset(
        dataset: String,
        payload: Dataset
    ): Flow<DgfrCallState<Dataset>>

    /**
     * Create a new badge for a given dataset
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    fun postAddDatasetBadge(
        dataset: String,
        payload: Badge
    ): Flow<DgfrCallState<Badge>>

    /**
     * Delete a badge for a given dataset
     * @param badgeKind (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteDatasetBadge(
        badgeKind: String,
        dataset: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Unmark the dataset as featured
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteUnfeatureDataset(
        dataset: String
    ): Flow<DgfrCallState<Dataset>>

    /**
     * Mark the dataset as featured
     * @param dataset The dataset ID or slug (required)
     */
    fun postFeatureDataset(
        dataset: String
    ): Flow<DgfrCallState<Dataset>>

    /**
     * @param dataset The dataset ID or slug (required)
     */
    fun getRdfDataset(
        dataset: String
    ): Flow<DgfrCallState<String>>

    /**
     * @param dataset The dataset ID or slug (required)
     * @param format (required)
     */
    fun getRdfDatasetFormat(
        dataset: String,
        format: String
    ): Flow<DgfrCallState<String>>

    /**
     * Create a new resource for a given dataset
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateResource(
        dataset: String,
        payload: Resource
    ): Flow<DgfrCallState<Resource>>

    /**
     * Reorder resources
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun putUpdateResources(
        dataset: String,
        payload: List<Resource>
    ): Flow<DgfrCallState<List<Resource>>>

    /**
     * Delete a given resource on a given dataset
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun deleteResource(
        rid: String,
        dataset: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Get a resource given its identifier
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getResource(
        rid: String,
        dataset: String
    ): Flow<DgfrCallState<Resource>>

    /**
     * Update a given resource on a given dataset
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun putUpdateResource(
        rid: String,
        dataset: String,
        payload: Resource
    ): Flow<DgfrCallState<Resource>>

    /**
     * Checks that a resource's URL exists and returns metadat
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    fun getCheckDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrCallState<Map<String, String>>>

    /**
     * Upload a file related to a given resource on a given da
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postUploadDatasetResource(
        rid: String,
        dataset: String
    ): Flow<DgfrCallState<UploadedResource>>

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
    @OptIn(MissingFieldMapping::class)
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
    ): Flow<DgfrCallState<UploadedResource?>>

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
    @OptIn(MissingFieldMapping::class)
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
    ): Flow<DgfrCallState<UploadedResource>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operatio
     * @param id (required)
     */
    fun deleteUnfollowDataset(
        id: String
    ): Flow<DgfrCallState<Boolean>>

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
    ): Flow<DgfrCallState<FollowPage>>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operatio
     * @param id (required)
     */
    fun postFollowDataset(
        id: String
    ): Flow<DgfrCallState<Boolean>>
}
