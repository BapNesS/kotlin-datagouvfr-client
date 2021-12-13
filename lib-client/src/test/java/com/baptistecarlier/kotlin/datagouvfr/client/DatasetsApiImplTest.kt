package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.DatasetsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockCommunityResource
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockDataset
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockDatasetPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@MissingFieldMapping
internal class DatasetsApiImplTest {

    private lateinit var apiImpl: DatasetsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = DatasetsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = DatasetsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListDatasets

    @Test
    fun `getListDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListDatasets(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<DatasetPage>)
        assert(results[1] is DgfrCallState.ClientError<DatasetPage>)
    }

    @Test
    fun `getListDatasets when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDatasetPage)

        val flow = apiImpl.getListDatasets(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<DatasetPage>)
        assert(results[1] is DgfrCallState.ServerError<DatasetPage>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `getListDatasets when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDatasetPage)

        val flow = apiImpl.getListDatasets(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<DatasetPage>)
        assert(results[1] is DgfrCallState.Success<DatasetPage>)
    }

    // endregion getListDatasets

    // region postCreateDataset

    @Test
    fun `postCreateDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateDataset(mockDataset)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ClientError<Dataset>)
    }

    @Test
    fun `postCreateDataset when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDataset)

        val flow = apiImpl.postCreateDataset(mockDataset)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ServerError<Dataset>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `postCreateDataset when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDataset)

        val flow = apiImpl.postCreateDataset(mockDataset)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.Success<Dataset>)
    }

    // endregion postCreateDataset

    // region getAvailableDatasetBadges

    @Test
    fun `getAvailableDatasetBadges when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getAvailableDatasetBadges()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Map<String, String>>)
        assert(results[1] is DgfrCallState.ClientError<Map<String, String>>)
    }

    @Test
    fun `getAvailableDatasetBadges when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyMap<String, String>())

        val flow = apiImpl.getAvailableDatasetBadges()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Map<String, String>>)
        assert(results[1] is DgfrCallState.ServerError<Map<String, String>>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `getAvailableDatasetBadges when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyMap<String, String>())

        val flow = apiImpl.getAvailableDatasetBadges()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Map<String, String>>)
        assert(results[1] is DgfrCallState.Success<Map<String, String>>)
    }

    // endregion getAvailableDatasetBadges

    // region getListCommunityResources

    @Test
    fun `getListCommunityResources when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListCommunityResources()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResourcePage>)
        assert(results[1] is DgfrCallState.ClientError<CommunityResourcePage>)
    }

    @Test
    fun `getListCommunityResources when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockCommunityResourcePage)

        val flow = apiImpl.getListCommunityResources()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResourcePage>)
        assert(results[1] is DgfrCallState.ServerError<CommunityResourcePage>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `getListCommunityResources when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockCommunityResourcePage)

        val flow = apiImpl.getListCommunityResources()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResourcePage>)
        assert(results[1] is DgfrCallState.Success<CommunityResourcePage>)
    }

    // endregion getListCommunityResources

    // region postCreateCommunityResource

    @Test
    fun `postCreateCommunityResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateCommunityResource(mockCommunityResource)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.ClientError<CommunityResource>)
    }

    @Test
    fun `postCreateCommunityResource when Http BadRequest then Loading+ServerError`() =
        runBlocking {
            mockClient(HttpStatusCode.BadRequest, mockCommunityResource)

            val flow = apiImpl.postCreateCommunityResource(mockCommunityResource)

            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrCallState.Loading<CommunityResource>)
            assert(results[1] is DgfrCallState.ServerError<CommunityResource>)
            Assert.assertEquals(
                (results[1] as DgfrCallState.ServerError).httpCode,
                HttpStatusCode.BadRequest.value
            )
        }

    @Test
    fun `postCreateCommunityResource when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockCommunityResource)

        val flow = apiImpl.postCreateCommunityResource(mockCommunityResource)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.Success<CommunityResource>)
    }

    // endregion postCreateCommunityResource

    // region deleteCommunityResource

    @Test
    fun `deleteCommunityResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.ClientError<CommunityResource>)
    }

    @Test
    fun `deleteCommunityResource when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockCommunityResource)

        val flow = apiImpl.deleteCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.ServerError<CommunityResource>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `deleteCommunityResource when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockCommunityResource)

        val flow = apiImpl.deleteCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.Success<CommunityResource>)
    }

    // endregion deleteCommunityResource

    // region getRetrieveCommunityResource

    @Test
    fun `getRetrieveCommunityResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getRetrieveCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.ClientError<CommunityResource>)
    }

    @Test
    fun `getRetrieveCommunityResource when Http BadRequest then Loading+ServerError`() =
        runBlocking {
            mockClient(HttpStatusCode.BadRequest, mockCommunityResource)

            val flow = apiImpl.getRetrieveCommunityResource("")

            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrCallState.Loading<CommunityResource>)
            assert(results[1] is DgfrCallState.ServerError<CommunityResource>)
            Assert.assertEquals(
                (results[1] as DgfrCallState.ServerError).httpCode,
                HttpStatusCode.BadRequest.value
            )
        }

    @Test
    fun `getRetrieveCommunityResource when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockCommunityResource)

        val flow = apiImpl.getRetrieveCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.Success<CommunityResource>)
    }

    // endregion getRetrieveCommunityResource

    // region putUpdateCommunityResource

    @Test
    fun `putUpdateCommunityResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateCommunityResource("", mockCommunityResource)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.ClientError<CommunityResource>)
    }

    @Test
    fun `putUpdateCommunityResource when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockCommunityResource)

        val flow = apiImpl.putUpdateCommunityResource("", mockCommunityResource)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.ServerError<CommunityResource>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `putUpdateCommunityResource when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockCommunityResource)

        val flow = apiImpl.putUpdateCommunityResource("", mockCommunityResource)

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<CommunityResource>)
        assert(results[1] is DgfrCallState.Success<CommunityResource>)
    }

    // endregion putUpdateCommunityResource

    // region postUploadCommunityResource

    @Test
    fun `postUploadCommunityResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postUploadCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource>)
        assert(results[1] is DgfrCallState.ClientError<UploadedResource>)
    }

    @Test
    fun `postUploadCommunityResource when Http BadRequest then Loading+ServerError`() =
        runBlocking {
            mockClient(HttpStatusCode.BadRequest, mockUploadedResource)

            val flow = apiImpl.postUploadCommunityResource("")

            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrCallState.Loading<UploadedResource>)
            assert(results[1] is DgfrCallState.ServerError<UploadedResource>)
            Assert.assertEquals(
                (results[1] as DgfrCallState.ServerError).httpCode,
                HttpStatusCode.BadRequest.value
            )
        }

    @Test
    fun `postUploadCommunityResource when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedResource)

        val flow = apiImpl.postUploadCommunityResource("")

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource>)
        assert(results[1] is DgfrCallState.Success<UploadedResource>)
    }

    // endregion postUploadCommunityResource

    // region getAllowedExtensions

    @Test
    fun `getAllowedExtensions when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getAllowedExtensions()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.ClientError<List<String>>)
    }

    @Test
    fun `getAllowedExtensions when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<String>())

        val flow = apiImpl.getAllowedExtensions()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.ServerError<List<String>>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `getAllowedExtensions when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<String>())

        val flow = apiImpl.getAllowedExtensions()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.Success<List<String>>)
    }

    // endregion getAllowedExtensions

    // region getListFrequencies

    @Test
    fun `getListFrequencies when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListFrequencies()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Frequency>>)
        assert(results[1] is DgfrCallState.ClientError<List<Frequency>>)
    }

    @Test
    fun `getListFrequencies when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Frequency>())

        val flow = apiImpl.getListFrequencies()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Frequency>>)
        assert(results[1] is DgfrCallState.ServerError<List<Frequency>>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `getListFrequencies when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Frequency>())

        val flow = apiImpl.getListFrequencies()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Frequency>>)
        assert(results[1] is DgfrCallState.Success<List<Frequency>>)
    }

    // endregion getListFrequencies

    // region getListLicenses

    @Test
    fun `getListLicenses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListLicenses()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<License>>)
        assert(results[1] is DgfrCallState.ClientError<List<License>>)
    }

    @Test
    fun `getListLicenses when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<License>())

        val flow = apiImpl.getListLicenses()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<License>>)
        assert(results[1] is DgfrCallState.ServerError<List<License>>)
        Assert.assertEquals(
            (results[1] as DgfrCallState.ServerError).httpCode,
            HttpStatusCode.BadRequest.value
        )
    }

    @Test
    fun `getListLicenses when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<License>())

        val flow = apiImpl.getListLicenses()

        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<License>>)
        assert(results[1] is DgfrCallState.Success<List<License>>)
    }

    // endregion getListLicenses

    // region getRedirectResource

    @Test
    fun `getRedirectResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getRedirectResource("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getRedirectResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, "")

        val flow = apiImpl.getRedirectResource("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getRedirectResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, "")

        val flow = apiImpl.getRedirectResource("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getRedirectResource

    // region getResourceTypes

    @Test
    fun `getResourceTypes when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getResourceTypes()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<ResourceType>>)
        assert(results[1] is DgfrCallState.ClientError<List<ResourceType>>)
    }

    @Test
    fun `getResourceTypes when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<ResourceType>())

        val flow = apiImpl.getResourceTypes()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<ResourceType>>)
        assert(results[1] is DgfrCallState.ServerError<List<ResourceType>>)
    }

    @Test
    fun `getResourceTypes when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<ResourceType>())

        val flow = apiImpl.getResourceTypes()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<ResourceType>>)
        assert(results[1] is DgfrCallState.Success<List<ResourceType>>)
    }

    // endregion getResourceTypes

    // region getSchemas

    @Test
    fun `getSchemas when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSchemas()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Schema>>)
        assert(results[1] is DgfrCallState.ClientError<List<Schema>>)
    }

    @Test
    fun `getSchemas when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Schema>())

        val flow = apiImpl.getSchemas()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Schema>>)
        assert(results[1] is DgfrCallState.ServerError<List<Schema>>)
    }

    @Test
    fun `getSchemas when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Schema>())

        val flow = apiImpl.getSchemas()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Schema>>)
        assert(results[1] is DgfrCallState.Success<List<Schema>>)
    }

    // endregion getSchemas

    // region getSuggestDatasets

    @Test
    fun `getSuggestDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestDatasets("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<DatasetSuggestion>>)
        assert(results[1] is DgfrCallState.ClientError<List<DatasetSuggestion>>)
    }

    @Test
    fun `getSuggestDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<DatasetSuggestion>())

        val flow = apiImpl.getSuggestDatasets("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<DatasetSuggestion>>)
        assert(results[1] is DgfrCallState.ServerError<List<DatasetSuggestion>>)
    }

    @Test
    fun `getSuggestDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<DatasetSuggestion>())

        val flow = apiImpl.getSuggestDatasets("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<DatasetSuggestion>>)
        assert(results[1] is DgfrCallState.Success<List<DatasetSuggestion>>)
    }

    // endregion getSuggestDatasets

    // region getSuggestFormats

    @Test
    fun `getSuggestFormats when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestFormats("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Format>>)
        assert(results[1] is DgfrCallState.ClientError<List<Format>>)
    }

    @Test
    fun `getSuggestFormats when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Format>())

        val flow = apiImpl.getSuggestFormats("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Format>>)
        assert(results[1] is DgfrCallState.ServerError<List<Format>>)
    }

    @Test
    fun `getSuggestFormats when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Format>())

        val flow = apiImpl.getSuggestFormats("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Format>>)
        assert(results[1] is DgfrCallState.Success<List<Format>>)
    }

    // endregion getSuggestFormats

    // region getSuggestMime

    @Test
    fun `getSuggestMime when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestMime("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Mime>>)
        assert(results[1] is DgfrCallState.ClientError<List<Mime>>)
    }

    @Test
    fun `getSuggestMime when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Mime>())

        val flow = apiImpl.getSuggestMime("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Mime>>)
        assert(results[1] is DgfrCallState.ServerError<List<Mime>>)
    }

    @Test
    fun `getSuggestMime when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Mime>())

        val flow = apiImpl.getSuggestMime("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Mime>>)
        assert(results[1] is DgfrCallState.Success<List<Mime>>)
    }

    // endregion getSuggestMime

    // region deleteDataset

    @Test
    fun `deleteDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, true)

        val flow = apiImpl.deleteDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, true)

        val flow = apiImpl.deleteDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteDataset

    // region getDataset

    @Test
    fun `getDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ClientError<Dataset>)
    }

    @Test
    fun `getDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDataset)

        val flow = apiImpl.getDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ServerError<Dataset>)
    }

    @Test
    fun `getDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDataset)

        val flow = apiImpl.getDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.Success<Dataset>)
    }

    // endregion getDataset

    // region putUpdateDataset

    @Test
    fun `putUpdateDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateDataset("", mockDataset)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ClientError<Dataset>)
    }

    @Test
    fun `putUpdateDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDataset)

        val flow = apiImpl.putUpdateDataset("", mockDataset)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ServerError<Dataset>)
    }

    @Test
    fun `putUpdateDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDataset)

        val flow = apiImpl.putUpdateDataset("", mockDataset)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.Success<Dataset>)
    }

    // endregion putUpdateDataset

    // region postAddDatasetBadge

    @Test
    fun `postAddDatasetBadge when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postAddDatasetBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Badge>)
        assert(results[1] is DgfrCallState.ClientError<Badge>)
    }

    @Test
    fun `postAddDatasetBadge when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBadge)

        val flow = apiImpl.postAddDatasetBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Badge>)
        assert(results[1] is DgfrCallState.ServerError<Badge>)
    }

    @Test
    fun `postAddDatasetBadge when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBadge)

        val flow = apiImpl.postAddDatasetBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Badge>)
        assert(results[1] is DgfrCallState.Success<Badge>)
    }

    // endregion postAddDatasetBadge

    // region deleteDatasetBadge

    @Test
    fun `deleteDatasetBadge when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteDatasetBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteDatasetBadge when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, true)

        val flow = apiImpl.deleteDatasetBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteDatasetBadge when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, true)

        val flow = apiImpl.deleteDatasetBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteDatasetBadge

    // region deleteUnfeatureDataset

    @Test
    fun `deleteUnfeatureDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnfeatureDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ClientError<Dataset>)
    }

    @Test
    fun `deleteUnfeatureDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDataset)

        val flow = apiImpl.deleteUnfeatureDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ServerError<Dataset>)
    }

    @Test
    fun `deleteUnfeatureDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDataset)

        val flow = apiImpl.deleteUnfeatureDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.Success<Dataset>)
    }

    // endregion deleteUnfeatureDataset

    // region postFeatureDataset

    @Test
    fun `postFeatureDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postFeatureDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ClientError<Dataset>)
    }

    @Test
    fun `postFeatureDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDataset)

        val flow = apiImpl.postFeatureDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.ServerError<Dataset>)
    }

    @Test
    fun `postFeatureDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDataset)

        val flow = apiImpl.postFeatureDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Dataset>)
        assert(results[1] is DgfrCallState.Success<Dataset>)
    }

    // endregion postFeatureDataset

    // region getRdfDataset

    @Test
    fun `getRdfDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getRdfDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getRdfDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, "")

        val flow = apiImpl.getRdfDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getRdfDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, "")

        val flow = apiImpl.getRdfDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getRdfDataset

    // region getRdfDatasetFormat

    @Test
    fun `getRdfDatasetFormat when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getRdfDatasetFormat("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getRdfDatasetFormat when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, "")

        val flow = apiImpl.getRdfDatasetFormat("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getRdfDatasetFormat when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, "")

        val flow = apiImpl.getRdfDatasetFormat("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getRdfDatasetFormat

    // region postCreateResource

    @Test
    fun `postCreateResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateResource("", mockResource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.ClientError<Resource>)
    }

    @Test
    fun `postCreateResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockResource)

        val flow = apiImpl.postCreateResource("", mockResource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.ServerError<Resource>)
    }

    @Test
    fun `postCreateResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockResource)

        val flow = apiImpl.postCreateResource("", mockResource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.Success<Resource>)
    }

    // endregion postCreateResource

    // region putUpdateResources

    @Test
    fun `putUpdateResources when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateResources("", emptyList<Resource>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Resource>>)
        assert(results[1] is DgfrCallState.ClientError<List<Resource>>)
    }

    @Test
    fun `putUpdateResources when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Resource>())

        val flow = apiImpl.putUpdateResources("", emptyList<Resource>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Resource>>)
        assert(results[1] is DgfrCallState.ServerError<List<Resource>>)
    }

    @Test
    fun `putUpdateResources when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Resource>())

        val flow = apiImpl.putUpdateResources("", emptyList<Resource>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Resource>>)
        assert(results[1] is DgfrCallState.Success<List<Resource>>)
    }

    // endregion putUpdateResources

    // region deleteResource

    @Test
    fun `deleteResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, true)

        val flow = apiImpl.deleteResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, true)

        val flow = apiImpl.deleteResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteResource

    // region getResource

    @Test
    fun `getResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.ClientError<Resource>)
    }

    @Test
    fun `getResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockResource)

        val flow = apiImpl.getResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.ServerError<Resource>)
    }

    @Test
    fun `getResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockResource)

        val flow = apiImpl.getResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.Success<Resource>)
    }

    // endregion getResource

    // region putUpdateResource

    @Test
    fun `putUpdateResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateResource("", "", mockResource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.ClientError<Resource>)
    }

    @Test
    fun `putUpdateResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockResource)

        val flow = apiImpl.putUpdateResource("", "", mockResource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.ServerError<Resource>)
    }

    @Test
    fun `putUpdateResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockResource)

        val flow = apiImpl.putUpdateResource("", "", mockResource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Resource>)
        assert(results[1] is DgfrCallState.Success<Resource>)
    }

    // endregion putUpdateResource

    // region getCheckDatasetResource

    @Test
    fun `getCheckDatasetResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getCheckDatasetResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Map<String, String>>)
        assert(results[1] is DgfrCallState.ClientError<Map<String, String>>)
    }

    @Test
    fun `getCheckDatasetResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyMap<String, String>())

        val flow = apiImpl.getCheckDatasetResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Map<String, String>>)
        assert(results[1] is DgfrCallState.ServerError<Map<String, String>>)
    }

    @Test
    fun `getCheckDatasetResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyMap<String, String>())

        val flow = apiImpl.getCheckDatasetResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Map<String, String>>)
        assert(results[1] is DgfrCallState.Success<Map<String, String>>)
    }

    // endregion getCheckDatasetResource

    // region postUploadDatasetResource

    @Test
    fun `postUploadDatasetResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postUploadDatasetResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource>)
        assert(results[1] is DgfrCallState.ClientError<UploadedResource>)
    }

    @Test
    fun `postUploadDatasetResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedResource)

        val flow = apiImpl.postUploadDatasetResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource>)
        assert(results[1] is DgfrCallState.ServerError<UploadedResource>)
    }

    @Test
    fun `postUploadDatasetResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedResource)

        val flow = apiImpl.postUploadDatasetResource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource>)
        assert(results[1] is DgfrCallState.Success<UploadedResource>)
    }

    // endregion postUploadDatasetResource

    // region postUploadNewDatasetResource

    @Test
    fun `postUploadNewDatasetResource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postUploadNewDatasetResource(
            dataset = "",
            file = ByteArray(0),
            fileName = "",
            contentType = "",
            uuid = null,
            partIndex = null,
            partByteOffset = null,
            totalParts = null,
            chunkSize = null
        )
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource?>)
        assert(results[1] is DgfrCallState.ClientError<UploadedResource?>)
    }

    @Test
    fun `postUploadNewDatasetResource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedResource)

        val flow = apiImpl.postUploadNewDatasetResource(
            dataset = "",
            file = ByteArray(0),
            fileName = "",
            contentType = "",
            uuid = null,
            partIndex = null,
            partByteOffset = null,
            totalParts = null,
            chunkSize = null
        )
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource?>)
        assert(results[1] is DgfrCallState.ServerError<UploadedResource?>)
    }

    @Test
    fun `postUploadNewDatasetResource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedResource)

        val flow = apiImpl.postUploadNewDatasetResource(
            dataset = "",
            file = ByteArray(0),
            fileName = "",
            contentType = "",
            uuid = null,
            partIndex = null,
            partByteOffset = null,
            totalParts = null,
            chunkSize = null
        )
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<UploadedResource?>)
        assert(results[1] is DgfrCallState.Success<UploadedResource?>)
    }

    // endregion postUploadNewDatasetResource

    // region postUploadNewCommunityResource

    @Test
    fun `postUploadNewCommunityResource when client error then Loading+ClientError`() =
        runBlocking {
            mockClientForClientError()

            val flow = apiImpl.postUploadNewCommunityResource(
                dataset = "",
                file = ByteArray(0),
                fileName = "",
                contentType = "",
                uuid = null,
                partIndex = null,
                partByteOffset = null,
                totalParts = null,
                chunkSize = null
            )
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrCallState.Loading<UploadedResource>)
            assert(results[1] is DgfrCallState.ClientError<UploadedResource>)
        }

    @Test
    fun `postUploadNewCommunityResource when client error then Loading+ServerError`() =
        runBlocking {
            mockClient(HttpStatusCode.BadRequest, mockUploadedResource)

            val flow = apiImpl.postUploadNewCommunityResource(
                dataset = "",
                file = ByteArray(0),
                fileName = "",
                contentType = "",
                uuid = null,
                partIndex = null,
                partByteOffset = null,
                totalParts = null,
                chunkSize = null
            )
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrCallState.Loading<UploadedResource>)
            assert(results[1] is DgfrCallState.ServerError<UploadedResource>)
        }

    @Test
    fun `postUploadNewCommunityResource when client error then Loading+Success`() =
        runBlocking {
            mockClient(HttpStatusCode.OK, mockUploadedResource)

            val flow = apiImpl.postUploadNewCommunityResource(
                dataset = "",
                file = ByteArray(0),
                fileName = "",
                contentType = "",
                uuid = null,
                partIndex = null,
                partByteOffset = null,
                totalParts = null,
                chunkSize = null
            )
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrCallState.Loading<UploadedResource>)
            assert(results[1] is DgfrCallState.Success<UploadedResource>)
        }

    // endregion postUploadNewCommunityResource

    // region deleteUnfollowDataset

    @Test
    fun `deleteUnfollowDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnfollowDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteUnfollowDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, true)

        val flow = apiImpl.deleteUnfollowDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteUnfollowDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, true)

        val flow = apiImpl.deleteUnfollowDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteUnfollowDataset

    // region getListDatasetFollowers

    @Test
    fun `getListDatasetFollowers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListDatasetFollowers("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<FollowPage>)
        assert(results[1] is DgfrCallState.ClientError<FollowPage>)
    }

    @Test
    fun `getListDatasetFollowers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockFollowPage)

        val flow = apiImpl.getListDatasetFollowers("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<FollowPage>)
        assert(results[1] is DgfrCallState.ServerError<FollowPage>)
    }

    @Test
    fun `getListDatasetFollowers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockFollowPage)

        val flow = apiImpl.getListDatasetFollowers("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<FollowPage>)
        assert(results[1] is DgfrCallState.Success<FollowPage>)
    }

    // endregion getListDatasetFollowers

    // region postFollowDataset

    @Test
    fun `postFollowDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postFollowDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `postFollowDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, true)

        val flow = apiImpl.postFollowDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `postFollowDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, true)

        val flow = apiImpl.postFollowDataset("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion postFollowDataset
}
