package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.HarvestApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class HarvestApiImplTest {

    private lateinit var apiImpl: HarvestApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = HarvestApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = HarvestApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getHarvestBackends

    @Test
    fun `getHarvestBackends when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getHarvestBackends()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestBackend>)
        assert(results[1] is DgfrCallState.ClientError<HarvestBackend>)
    }

    @Test
    fun `getHarvestBackends when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestBackend)

        val flow = apiImpl.getHarvestBackends()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestBackend>)
        assert(results[1] is DgfrCallState.ServerError<HarvestBackend>)
    }

    @Test
    fun `getHarvestBackends when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestBackend)

        val flow = apiImpl.getHarvestBackends()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestBackend>)
        assert(results[1] is DgfrCallState.Success<HarvestBackend>)
    }

    // endregion getHarvestBackends

    // region getHarvestJob

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getHarvestJob when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getHarvestJob("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPage>)
        assert(results[1] is DgfrCallState.ClientError<HarvestJobPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getHarvestJob when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestJobPage)

        val flow = apiImpl.getHarvestJob("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPage>)
        assert(results[1] is DgfrCallState.ServerError<HarvestJobPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getHarvestJob when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestJobPage)

        val flow = apiImpl.getHarvestJob("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPage>)
        assert(results[1] is DgfrCallState.Success<HarvestJobPage>)
    }

    // endregion getHarvestJob

    // region getListHarvesterApi

    @Test
    fun `getListHarvesterApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListHarvesterApi()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.ClientError<List<String>>)
    }

    @Test
    fun `getListHarvesterApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<String>())

        val flow = apiImpl.getListHarvesterApi()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.ServerError<List<String>>)
    }

    @Test
    fun `getListHarvesterApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<String>())

        val flow = apiImpl.getListHarvesterApi()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.Success<List<String>>)
    }

    // endregion getListHarvesterApi

    // region postPreviewHarvestSourceConfig

    @Test
    fun `postPreviewHarvestSourceConfig when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postPreviewHarvestSourceConfig(mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPreview>)
        assert(results[1] is DgfrCallState.ClientError<HarvestJobPreview>)
    }

    @Test
    fun `postPreviewHarvestSourceConfig when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestJobPreview)

        val flow = apiImpl.postPreviewHarvestSourceConfig(mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPreview>)
        assert(results[1] is DgfrCallState.ServerError<HarvestJobPreview>)
    }

    @Test
    fun `postPreviewHarvestSourceConfig when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestJobPreview)

        val flow = apiImpl.postPreviewHarvestSourceConfig(mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPreview>)
        assert(results[1] is DgfrCallState.Success<HarvestJobPreview>)
    }

    // endregion postPreviewHarvestSourceConfig

    // region deleteHarvestSource

    @Test
    fun `deleteHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `deleteHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.deleteHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `deleteHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.deleteHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion deleteHarvestSource

    // region getHarvestSource

    @Test
    fun `getHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `getHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.getHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `getHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.getHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion getHarvestSource

    // region putUpdateHarvestSource

    @Test
    fun `putUpdateHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateHarvestSource("", mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `putUpdateHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.putUpdateHarvestSource("", mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `putUpdateHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.putUpdateHarvestSource("", mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion putUpdateHarvestSource

    // region getListHarvestJobs

    @Test
    fun `getListHarvestJobs when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListHarvestJobs("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJob>)
        assert(results[1] is DgfrCallState.ClientError<HarvestJob>)
    }

    @Test
    fun `getListHarvestJobs when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestJob)

        val flow = apiImpl.getListHarvestJobs("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJob>)
        assert(results[1] is DgfrCallState.ServerError<HarvestJob>)
    }

    @Test
    fun `getListHarvestJobs when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestJob)

        val flow = apiImpl.getListHarvestJobs("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJob>)
        assert(results[1] is DgfrCallState.Success<HarvestJob>)
    }

    // endregion getListHarvestJobs

    // region getPreviewHarvestSource

    @Test
    fun `getPreviewHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getPreviewHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPreview>)
        assert(results[1] is DgfrCallState.ClientError<HarvestJobPreview>)
    }

    @Test
    fun `getPreviewHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestJobPreview)

        val flow = apiImpl.getPreviewHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPreview>)
        assert(results[1] is DgfrCallState.ServerError<HarvestJobPreview>)
    }

    @Test
    fun `getPreviewHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestJobPreview)

        val flow = apiImpl.getPreviewHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestJobPreview>)
        assert(results[1] is DgfrCallState.Success<HarvestJobPreview>)
    }

    // endregion getPreviewHarvestSource

    // region deleteUnscheduleHarvestSource

    @Test
    fun `deleteUnscheduleHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnscheduleHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `deleteUnscheduleHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.deleteUnscheduleHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `deleteUnscheduleHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.deleteUnscheduleHarvestSource("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion deleteUnscheduleHarvestSource

    // region postScheduleHarvestSource

    @Test
    fun `postScheduleHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postScheduleHarvestSource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `postScheduleHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.postScheduleHarvestSource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `postScheduleHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.postScheduleHarvestSource("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion postScheduleHarvestSource

    // region postValidateHarvestSource

    @Test
    fun `postValidateHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postValidateHarvestSource("", mockHarvestSourceValidation)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `postValidateHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.postValidateHarvestSource("", mockHarvestSourceValidation)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `postValidateHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.postValidateHarvestSource("", mockHarvestSourceValidation)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion postValidateHarvestSource

    // region getListHarvestSources

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListHarvestSources when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListHarvestSources(null, null, null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<HarvestSourcePage>>)
        assert(results[1] is DgfrCallState.ClientError<List<HarvestSourcePage>>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListHarvestSources when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<HarvestSourcePage>())

        val flow = apiImpl.getListHarvestSources(null, null, null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<HarvestSourcePage>>)
        assert(results[1] is DgfrCallState.ServerError<List<HarvestSourcePage>>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListHarvestSources when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<HarvestSourcePage>())

        val flow = apiImpl.getListHarvestSources(null, null, null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<HarvestSourcePage>>)
        assert(results[1] is DgfrCallState.Success<List<HarvestSourcePage>>)
    }

    // endregion getListHarvestSources

    // region postCreateHarvestSource

    @Test
    fun `postCreateHarvestSource when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateHarvestSource(mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ClientError<HarvestSource>)
    }

    @Test
    fun `postCreateHarvestSource when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockHarvestSource)

        val flow = apiImpl.postCreateHarvestSource(mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.ServerError<HarvestSource>)
    }

    @Test
    fun `postCreateHarvestSource when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockHarvestSource)

        val flow = apiImpl.postCreateHarvestSource(mockHarvestSource)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<HarvestSource>)
        assert(results[1] is DgfrCallState.Success<HarvestSource>)
    }

    // endregion postCreateHarvestSource
}
