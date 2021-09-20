package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
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
    suspend fun getListDatasets(
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
    ): Flow<DatasetPage?>

    /**
     * Create a new dataset
     * @param payload (required)
     */
    suspend fun postCreateDataset(
        payload: Dataset
    ): Flow<Dataset?>

    /**
     * List all available dataset badges and their labels
     */
    suspend fun getAvailableDatasetBadges(): Flow<Map<String, String>?>

    /**
     * List all community resources
     * @param sort The sorting attribute (optional, default to -created)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param organization Filter activities for that particular organization (optional)
     * @param dataset Filter activities for that particular dataset (optional)
     * @param owner Filter activities for that particula
     */
    suspend fun getListCommunityResources(
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null,
        organization: String? = null,
        dataset: String? = null,
        owner: String? = null
    ): Flow<CommunityResourcePage?>

    /**
     * Create a new community resource
     * @param payload (required)
     */
    suspend fun postCreateCommunityResource(
        payload: CommunityResource
    ): Flow<CommunityResource?>

    /**
     * Delete a given community resource
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    suspend fun deleteCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<CommunityResource?>

    /**
     * Retrieve a community resource given its identifier
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    suspend fun getRetrieveCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<CommunityResource?>

    /**
     * Update a given community resource
     * @param community The community resource unique identifier (required)
     * @param payload (required)
     * @param dataset The dataset ID or slug (optional)
     */
    suspend fun putUpdateCommunityResource(
        community: String,
        payload: CommunityResource,
        dataset: String? = null
    ): Flow<CommunityResource?>

    /**
     * Update the file related to a given community resource
     * @param community The community resource unique identifier (required)
     * @param dataset The dataset ID or slug (optional)
     */
    suspend fun postUploadCommunityResource(
        community: String,
        dataset: String? = null
    ): Flow<UploadedResource?>

    /**
     * List all allowed resources extensions
     */
    suspend fun getAllowedExtensions(): Flow<List<String>?>

    /**
     * List all available frequencies
     */
    suspend fun getListFrequencies(
    ): Flow<List<Frequency>?>

    /**
     * List all available licenses
     */
    suspend fun getListLicenses(
    ): Flow<List<License>?>

    /**
     * Redirect to the latest version of a resource given its
     * @param id (required)
     * @param dataset The dataset ID or slug (optional)
     */
    suspend fun getRedirectResource(
        id: String,
        dataset: String?
    ): Flow<String?>

    /**
     * List all resource types
     */
    suspend fun getResourceTypes(
    ): Flow<List<ResourceType>?>

    /**
     * List all available schemas
     */
    suspend fun getSchemas(
    ): Flow<List<Schema>?>

    /**
     * Suggest datasets
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (op
     */
    suspend fun getSuggestDatasets(
        q: String,
        size: Int? = null
    ): Flow<List<DatasetSuggestion>?>

    /**
     * Suggest file formats
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    suspend fun getSuggestFormats(
        q: String,
        size: Int?
    ): Flow<List<Format>?>

    /**
     * Suggest mime types
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    suspend fun getSuggestMime(
        q: String,
        size: Int?
    ): Flow<List<Mime>?>

    /**
     * Delete a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun deleteDataset(
        dataset: String
    ): Flow<Boolean?>

    /**
     * Get a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun getDataset(
        dataset: String
    ): Flow<Dataset?>

    /**
     * Update a dataset given its identifier
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateDataset(
        dataset: String,
        payload: Dataset
    ): Flow<Dataset?>

    /**
     * Create a new badge for a given dataset
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    suspend fun postAddDatasetBadge(
        dataset: String,
        payload: Badge
    ): Flow<Badge?>

    /**
     * Delete a badge for a given dataset
     * @param badgeKind (required)
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun deleteDatasetBadge(
        badgeKind: String,
        dataset: String
    ): Flow<Boolean?>

    /**
     * Unmark the dataset as featured
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun deleteUnfeatureDataset(
        dataset: String
    ): Flow<Dataset?>

    /**
     * Mark the dataset as featured
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun postFeatureDataset(
        dataset: String
    ): Flow<Dataset?>

    /**
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun getRdfDataset(
        dataset: String
    ): Flow<String?>

    /**
     * @param dataset The dataset ID or slug (required)
     * @param format (required)
     */
    suspend fun getRdfDatasetFormat(
        dataset: String,
        format: String
    ): Flow<String?>

    /**
     * Create a new resource for a given dataset
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    suspend fun postCreateResource(
        dataset: String,
        payload: Resource
    ): Flow<Resource?>

    /**
     * Reorder resources
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateResources(
        dataset: String,
        payload: List<Resource>
    ): Flow<List<Resource>?>

    /**
     * Delete a given resource on a given dataset
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun deleteResource(
        rid: String,
        dataset: String
    ): Flow<Boolean?>

    /**
     * Get a resource given its identifier
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun getResource(
        rid: String,
        dataset: String
    ): Flow<Resource?>

    /**
     * Update a given resource on a given dataset
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateResource(
        rid: String,
        dataset: String,
        payload: Resource
    ): Flow<Resource?>

    /**
     * Checks that a resource's URL exists and returns metadat
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun getCheckDatasetResource(
        rid: String,
        dataset: String
    ): Flow<Map<String, String>?>

    /**
     * Upload a file related to a given resource on a given da
     * @param rid The resource unique identifier (required)
     * @param dataset The dataset ID or slug (required)
     */
    suspend fun postUploadDatasetResource(
        rid: String,
        dataset: String
    ): Flow<UploadedResource?>

    /**
     * Upload a new dataset resource
     * @param dataset The dataset ID or slug (required)
     * @param file (optional)
     * @param uuid (optional)
     * @param filename (optional)
     * @param partindex (optional)
     * @param partbyteoffset (optional)
     * @param totalparts (optional)
     * @param chunksize (optional)
     */
    /*suspend fun postUploadNewDatasetResource(
        dataset: String,
        file: RequestBody? = null,
        uuid: String? = null,
        filename: String? = null,
        partindex: Int? = null,
        partbyteoffset: Int? = null,
        totalparts: Int? = null,
        chunksize: Int? = null
    ): Flow<UploadedResource?>*/

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
    /*suspend fun postUploadNewCommunityResource(
        dataset: String,
        file: RequestBody? = null,
        uuid: String? = null,
        filename: String? = null,
        partindex: Int? = null,
        partbyteoffset: Int? = null,
        totalparts: Int? = null,
        chunksize: Int? = null
    ): Flow<UploadedResource?>*/

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operatio
     * @param id (required)
     */
    suspend fun deleteUnfollowDataset(
        id: String
    ): Flow<Boolean?>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional,
     */
    suspend fun getListDatasetFollowers(
        id: String,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<FollowPage?>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operatio
     * @param id (required)
     */
    suspend fun postFollowDataset(
        id: String
    ): Flow<Boolean?>

}