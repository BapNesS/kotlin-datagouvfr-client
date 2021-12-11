package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.ReusesApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class ReusesApiImplTest {

    private lateinit var apiImpl: ReusesApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = ReusesApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = ReusesApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListReuses

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<ReusePage>)
        assert(results[1] is DgfrResource.ClientError<ReusePage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReusePage)

        val flow = apiImpl.getListReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<ReusePage>)
        assert(results[1] is DgfrResource.ServerError<ReusePage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReusePage)

        val flow = apiImpl.getListReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<ReusePage>)
        assert(results[1] is DgfrResource.Success<ReusePage>)
    }

    // endregion getListReuses

    // region postCreateReuse

    @Test
    fun `postCreateReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateReuse(mockReuse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ClientError<Reuse>)
    }

    @Test
    fun `postCreateReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReuse)

        val flow = apiImpl.postCreateReuse(mockReuse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ServerError<Reuse>)
    }

    @Test
    fun `postCreateReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReuse)

        val flow = apiImpl.postCreateReuse(mockReuse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.Success<Reuse>)
    }

    // endregion postCreateReuse

    // region getAvailableReuseBadges

    @Test
    fun `getAvailableReuseBadges when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getAvailableReuseBadges()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `getAvailableReuseBadges when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.getAvailableReuseBadges()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `getAvailableReuseBadges when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.getAvailableReuseBadges()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion getAvailableReuseBadges

    // region getSuggestReuses

    @Test
    fun `getSuggestReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestReuses("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<ReuseSuggestion>>)
        assert(results[1] is DgfrResource.ClientError<List<ReuseSuggestion>>)
    }

    @Test
    fun `getSuggestReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<ReuseSuggestion>())

        val flow = apiImpl.getSuggestReuses("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<ReuseSuggestion>>)
        assert(results[1] is DgfrResource.ServerError<List<ReuseSuggestion>>)
    }

    @Test
    fun `getSuggestReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<ReuseSuggestion>())

        val flow = apiImpl.getSuggestReuses("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<ReuseSuggestion>>)
        assert(results[1] is DgfrResource.Success<List<ReuseSuggestion>>)
    }

    // endregion getSuggestReuses

    // region getReuseTypes

    @Test
    fun `getReuseTypes when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getReuseTypes()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<ReuseType>>)
        assert(results[1] is DgfrResource.ClientError<List<ReuseType>>)
    }

    @Test
    fun `getReuseTypes when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<ReuseType>())

        val flow = apiImpl.getReuseTypes()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<ReuseType>>)
        assert(results[1] is DgfrResource.ServerError<List<ReuseType>>)
    }

    @Test
    fun `getReuseTypes when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<ReuseType>())

        val flow = apiImpl.getReuseTypes()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<ReuseType>>)
        assert(results[1] is DgfrResource.Success<List<ReuseType>>)
    }

    // endregion getReuseTypes

    // region deleteUnfollowReuse

    @Test
    fun `deleteUnfollowReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnfollowReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteUnfollowReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteUnfollowReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteUnfollowReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteUnfollowReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteUnfollowReuse

    // region getListReuseFollowers

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListReuseFollowers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListReuseFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.ClientError<FollowPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListReuseFollowers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockFollowPage)

        val flow = apiImpl.getListReuseFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.ServerError<FollowPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListReuseFollowers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockFollowPage)

        val flow = apiImpl.getListReuseFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.Success<FollowPage>)
    }

    // endregion getListReuseFollowers

    // region postFollowReuse

    @Test
    fun `postFollowReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postFollowReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `postFollowReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.postFollowReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `postFollowReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.postFollowReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion postFollowReuse

    // region deleteReuse

    @Test
    fun `deleteReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteReuse

    // region getReuse

    @Test
    fun `getReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ClientError<Reuse>)
    }

    @Test
    fun `getReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReuse)

        val flow = apiImpl.getReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ServerError<Reuse>)
    }

    @Test
    fun `getReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReuse)

        val flow = apiImpl.getReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.Success<Reuse>)
    }

    // endregion getReuse

    // region putUpdateReuse

    @Test
    fun `putUpdateReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateReuse("", mockReuse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ClientError<Reuse>)
    }

    @Test
    fun `putUpdateReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReuse)

        val flow = apiImpl.putUpdateReuse("", mockReuse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ServerError<Reuse>)
    }

    @Test
    fun `putUpdateReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReuse)

        val flow = apiImpl.putUpdateReuse("", mockReuse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.Success<Reuse>)
    }

    // endregion putUpdateReuse

    // region postAddReuseBadge

    @Test
    fun `postAddReuseBadge when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postAddReuseBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Badge>)
        assert(results[1] is DgfrResource.ClientError<Badge>)
    }

    @Test
    fun `postAddReuseBadge when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBadge)

        val flow = apiImpl.postAddReuseBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Badge>)
        assert(results[1] is DgfrResource.ServerError<Badge>)
    }

    @Test
    fun `postAddReuseBadge when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBadge)

        val flow = apiImpl.postAddReuseBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Badge>)
        assert(results[1] is DgfrResource.Success<Badge>)
    }

    // endregion postAddReuseBadge

    // region deleteReuseBadge

    @Test
    fun `deleteReuseBadge when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteReuseBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteReuseBadge when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteReuseBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteReuseBadge when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteReuseBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteReuseBadge

    // region postReuseAddDataset

    @Test
    fun `postReuseAddDataset when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postReuseAddDataset("", mockDatasetReference)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ClientError<Reuse>)
    }

    @Test
    fun `postReuseAddDataset when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReuse)

        val flow = apiImpl.postReuseAddDataset("", mockDatasetReference)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ServerError<Reuse>)
    }

    @Test
    fun `postReuseAddDataset when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReuse)

        val flow = apiImpl.postReuseAddDataset("", mockDatasetReference)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.Success<Reuse>)
    }

    // endregion postReuseAddDataset

    // region deleteUnfeatureReuse

    @Test
    fun `deleteUnfeatureReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnfeatureReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ClientError<Reuse>)
    }

    @Test
    fun `deleteUnfeatureReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReuse)

        val flow = apiImpl.deleteUnfeatureReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ServerError<Reuse>)
    }

    @Test
    fun `deleteUnfeatureReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReuse)

        val flow = apiImpl.deleteUnfeatureReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.Success<Reuse>)
    }

    // endregion deleteUnfeatureReuse

    // region postFeatureReuse

    @Test
    fun `postFeatureReuse when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postFeatureReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ClientError<Reuse>)
    }

    @Test
    fun `postFeatureReuse when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockReuse)

        val flow = apiImpl.postFeatureReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.ServerError<Reuse>)
    }

    @Test
    fun `postFeatureReuse when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockReuse)

        val flow = apiImpl.postFeatureReuse("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Reuse>)
        assert(results[1] is DgfrResource.Success<Reuse>)
    }

    // endregion postFeatureReuse

    // region postReuseImage

    @Test
    fun `postReuseImage when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postReuseImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `postReuseImage when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.postReuseImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `postReuseImage when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.postReuseImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion postReuseImage
}
