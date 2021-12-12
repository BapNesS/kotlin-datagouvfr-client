package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.MeApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockBoolean
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockMe
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class MeApiImplTest {

    private lateinit var apiImpl: MeApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = MeApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = MeApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region deleteMe

    @Test
    fun `deleteMe when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteMe()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteMe when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteMe()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteMe when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteMe()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteMe

    // region getMe

    @Test
    fun `getMe when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMe()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Me>)
        assert(results[1] is DgfrResource.ClientError<Me>)
    }

    @Test
    fun `getMe when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockMe)

        val flow = apiImpl.getMe()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Me>)
        assert(results[1] is DgfrResource.ServerError<Me>)
    }

    @Test
    fun `getMe when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockMe)

        val flow = apiImpl.getMe()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Me>)
        assert(results[1] is DgfrResource.Success<Me>)
    }

    // endregion getMe

    // region putUpdateMe

    @Test
    fun `putUpdateMe when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateMe(mockMe)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Me>)
        assert(results[1] is DgfrResource.ClientError<Me>)
    }

    @Test
    fun `putUpdateMe when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockMe)

        val flow = apiImpl.putUpdateMe(mockMe)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Me>)
        assert(results[1] is DgfrResource.ServerError<Me>)
    }

    @Test
    fun `putUpdateMe when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockMe)

        val flow = apiImpl.putUpdateMe(mockMe)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Me>)
        assert(results[1] is DgfrResource.Success<Me>)
    }

    // endregion putUpdateMe

    // region deleteClearApikey

    @Test
    fun `deleteClearApikey when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteClearApikey()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteClearApikey when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteClearApikey()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteClearApikey when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteClearApikey()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteClearApikey

    // region postGenerateApikey

    @Test
    fun `postGenerateApikey when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postGenerateApikey()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<ApiKey>)
        assert(results[1] is DgfrResource.ClientError<ApiKey>)
    }

    @Test
    fun `postGenerateApikey when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockApiKey)

        val flow = apiImpl.postGenerateApikey()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<ApiKey>)
        assert(results[1] is DgfrResource.ServerError<ApiKey>)
    }

    @Test
    fun `postGenerateApikey when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockApiKey)

        val flow = apiImpl.postGenerateApikey()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<ApiKey>)
        assert(results[1] is DgfrResource.Success<ApiKey>)
    }

    // endregion postGenerateApikey

    // region postMyAvatar

    @Test
    fun `postMyAvatar when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postMyAvatar(ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `postMyAvatar when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.postMyAvatar(ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `postMyAvatar when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.postMyAvatar(ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion postMyAvatar

    // region getMyDatasets

    @Test
    fun `getMyDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Dataset>>)
        assert(results[1] is DgfrResource.ClientError<List<Dataset>>)
    }

    @Test
    fun `getMyDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Dataset>())

        val flow = apiImpl.getMyDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Dataset>>)
        assert(results[1] is DgfrResource.ServerError<List<Dataset>>)
    }

    @Test
    fun `getMyDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Dataset>())

        val flow = apiImpl.getMyDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Dataset>>)
        assert(results[1] is DgfrResource.Success<List<Dataset>>)
    }

    // endregion getMyDatasets

    // region getMyMetrics

    @Test
    fun `getMyMetrics when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyMetrics()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<MyMetrics>>)
        assert(results[1] is DgfrResource.ClientError<List<MyMetrics>>)
    }

    @Test
    fun `getMyMetrics when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<MyMetrics>())

        val flow = apiImpl.getMyMetrics()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<MyMetrics>>)
        assert(results[1] is DgfrResource.ServerError<List<MyMetrics>>)
    }

    @Test
    fun `getMyMetrics when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<MyMetrics>())

        val flow = apiImpl.getMyMetrics()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<MyMetrics>>)
        assert(results[1] is DgfrResource.Success<List<MyMetrics>>)
    }

    // endregion getMyMetrics

    // region getMyOrgCommunityResources

    @Test
    fun `getMyOrgCommunityResources when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyOrgCommunityResources()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<CommunityResource>>)
        assert(results[1] is DgfrResource.ClientError<List<CommunityResource>>)
    }

    @Test
    fun `getMyOrgCommunityResources when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<CommunityResource>())

        val flow = apiImpl.getMyOrgCommunityResources()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<CommunityResource>>)
        assert(results[1] is DgfrResource.ServerError<List<CommunityResource>>)
    }

    @Test
    fun `getMyOrgCommunityResources when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<CommunityResource>())

        val flow = apiImpl.getMyOrgCommunityResources()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<CommunityResource>>)
        assert(results[1] is DgfrResource.Success<List<CommunityResource>>)
    }

    // endregion getMyOrgCommunityResources

    // region getMyOrgDatasets

    @Test
    fun `getMyOrgDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyOrgDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Dataset>>)
        assert(results[1] is DgfrResource.ClientError<List<Dataset>>)
    }

    @Test
    fun `getMyOrgDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Dataset>())

        val flow = apiImpl.getMyOrgDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Dataset>>)
        assert(results[1] is DgfrResource.ServerError<List<Dataset>>)
    }

    @Test
    fun `getMyOrgDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Dataset>())

        val flow = apiImpl.getMyOrgDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Dataset>>)
        assert(results[1] is DgfrResource.Success<List<Dataset>>)
    }

    // endregion getMyOrgDatasets

    // region getMyOrgDiscussions

    @Test
    fun `getMyOrgDiscussions when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyOrgDiscussions()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Discussion>>)
        assert(results[1] is DgfrResource.ClientError<List<Discussion>>)
    }

    @Test
    fun `getMyOrgDiscussions when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Discussion>())

        val flow = apiImpl.getMyOrgDiscussions()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Discussion>>)
        assert(results[1] is DgfrResource.ServerError<List<Discussion>>)
    }

    @Test
    fun `getMyOrgDiscussions when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Discussion>())

        val flow = apiImpl.getMyOrgDiscussions()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Discussion>>)
        assert(results[1] is DgfrResource.Success<List<Discussion>>)
    }

    // endregion getMyOrgDiscussions

    // region getMyOrgIssues

    @Test
    fun `getMyOrgIssues when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyOrgIssues()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Issue>>)
        assert(results[1] is DgfrResource.ClientError<List<Issue>>)
    }

    @Test
    fun `getMyOrgIssues when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Issue>())

        val flow = apiImpl.getMyOrgIssues()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Issue>>)
        assert(results[1] is DgfrResource.ServerError<List<Issue>>)
    }

    @Test
    fun `getMyOrgIssues when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Issue>())

        val flow = apiImpl.getMyOrgIssues()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Issue>>)
        assert(results[1] is DgfrResource.Success<List<Issue>>)
    }

    // endregion getMyOrgIssues

    // region getMyOrgReuses

    @Test
    fun `getMyOrgReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyOrgReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.ClientError<List<Reuse>>)
    }

    @Test
    fun `getMyOrgReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Reuse>())

        val flow = apiImpl.getMyOrgReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.ServerError<List<Reuse>>)
    }

    @Test
    fun `getMyOrgReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Reuse>())

        val flow = apiImpl.getMyOrgReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.Success<List<Reuse>>)
    }

    // endregion getMyOrgReuses

    // region getMyReuses

    @Test
    fun `getMyReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getMyReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.ClientError<List<Reuse>>)
    }

    @Test
    fun `getMyReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Reuse>())

        val flow = apiImpl.getMyReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.ServerError<List<Reuse>>)
    }

    @Test
    fun `getMyReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Reuse>())

        val flow = apiImpl.getMyReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.Success<List<Reuse>>)
    }

    // endregion getMyReuses
}
