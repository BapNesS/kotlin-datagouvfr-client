package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.SiteApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockOembed
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockSite
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockString
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class SiteApiImplTest {

    private lateinit var apiImpl: SiteApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = SiteApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = SiteApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getActivity

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getActivity when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getActivity()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<ActivityPage>>)
        assert(results[1] is DgfrCallState.ClientError<List<ActivityPage>>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getActivity when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<ActivityPage>())

        val flow = apiImpl.getActivity()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<ActivityPage>>)
        assert(results[1] is DgfrCallState.ServerError<List<ActivityPage>>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getActivity when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<ActivityPage>())

        val flow = apiImpl.getActivity()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<ActivityPage>>)
        assert(results[1] is DgfrCallState.Success<List<ActivityPage>>)
    }

    // endregion getActivity

    // region getOembed

    @Test
    fun `getOembed when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getOembed("", null, null, "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Oembed>)
        assert(results[1] is DgfrCallState.ClientError<Oembed>)
    }

    @Test
    fun `getOembed when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockOembed)

        val flow = apiImpl.getOembed("", null, null, "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Oembed>)
        assert(results[1] is DgfrCallState.ServerError<Oembed>)
    }

    @Test
    fun `getOembed when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockOembed)

        val flow = apiImpl.getOembed("", null, null, "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Oembed>)
        assert(results[1] is DgfrCallState.Success<Oembed>)
    }

    // endregion getOembed

    // region getOembeds

    @Test
    fun `getOembeds when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getOembeds("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Oembed>>)
        assert(results[1] is DgfrCallState.ClientError<List<Oembed>>)
    }

    @Test
    fun `getOembeds when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Oembed>())

        val flow = apiImpl.getOembeds("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Oembed>>)
        assert(results[1] is DgfrCallState.ServerError<List<Oembed>>)
    }

    @Test
    fun `getOembeds when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Oembed>())

        val flow = apiImpl.getOembeds("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Oembed>>)
        assert(results[1] is DgfrCallState.Success<List<Oembed>>)
    }

    // endregion getOembeds

    // region getSite

    @Test
    fun `getSite when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSite()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Site>)
        assert(results[1] is DgfrCallState.ClientError<Site>)
    }

    @Test
    fun `getSite when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockSite)

        val flow = apiImpl.getSite()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Site>)
        assert(results[1] is DgfrCallState.ServerError<Site>)
    }

    @Test
    fun `getSite when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockSite)

        val flow = apiImpl.getSite()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Site>)
        assert(results[1] is DgfrCallState.Success<Site>)
    }

    // endregion getSite

    // region getSiteRdfCatalog

    @Test
    fun `getSiteRdfCatalog when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSiteRdfCatalog()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getSiteRdfCatalog when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockString)

        val flow = apiImpl.getSiteRdfCatalog()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getSiteRdfCatalog when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockString)

        val flow = apiImpl.getSiteRdfCatalog()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getSiteRdfCatalog

    // region getSiteRdfCatalogFormat

    @Test
    fun `getSiteRdfCatalogFormat when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSiteRdfCatalogFormat("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getSiteRdfCatalogFormat when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockString)

        val flow = apiImpl.getSiteRdfCatalogFormat("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getSiteRdfCatalogFormat when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockString)

        val flow = apiImpl.getSiteRdfCatalogFormat("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getSiteRdfCatalogFormat

    // region getSiteJsonLdContext

    @Test
    fun `getSiteJsonLdContext when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSiteJsonLdContext()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getSiteJsonLdContext when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockString)

        val flow = apiImpl.getSiteJsonLdContext()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getSiteJsonLdContext when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockString)

        val flow = apiImpl.getSiteJsonLdContext()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getSiteJsonLdContext

    // region getSiteDataPortal

    @Test
    fun `getSiteDataPortal when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSiteDataPortal("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ClientError<String>)
    }

    @Test
    fun `getSiteDataPortal when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockString)

        val flow = apiImpl.getSiteDataPortal("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.ServerError<String>)
    }

    @Test
    fun `getSiteDataPortal when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockString)

        val flow = apiImpl.getSiteDataPortal("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<String>)
        assert(results[1] is DgfrCallState.Success<String>)
    }

    // endregion getSiteDataPortal

    // region getHomeDatasets

    @Test
    fun `getHomeDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getHomeDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Dataset>>)
        assert(results[1] is DgfrCallState.ClientError<List<Dataset>>)
    }

    @Test
    fun `getHomeDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Dataset>())

        val flow = apiImpl.getHomeDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Dataset>>)
        assert(results[1] is DgfrCallState.ServerError<List<Dataset>>)
    }

    @Test
    fun `getHomeDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Dataset>())

        val flow = apiImpl.getHomeDatasets()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Dataset>>)
        assert(results[1] is DgfrCallState.Success<List<Dataset>>)
    }

    // endregion getHomeDatasets

    // region putSetHomeDatasets

    @Test
    fun `putSetHomeDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putSetHomeDatasets(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Dataset>>)
        assert(results[1] is DgfrCallState.ClientError<List<Dataset>>)
    }

    @Test
    fun `putSetHomeDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Dataset>())

        val flow = apiImpl.putSetHomeDatasets(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Dataset>>)
        assert(results[1] is DgfrCallState.ServerError<List<Dataset>>)
    }

    @Test
    fun `putSetHomeDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Dataset>())

        val flow = apiImpl.putSetHomeDatasets(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Dataset>>)
        assert(results[1] is DgfrCallState.Success<List<Dataset>>)
    }

    // endregion putSetHomeDatasets

    // region getHomeReuses

    @Test
    fun `getHomeReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getHomeReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Reuse>>)
        assert(results[1] is DgfrCallState.ClientError<List<Reuse>>)
    }

    @Test
    fun `getHomeReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Reuse>())

        val flow = apiImpl.getHomeReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Reuse>>)
        assert(results[1] is DgfrCallState.ServerError<List<Reuse>>)
    }

    @Test
    fun `getHomeReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Reuse>())

        val flow = apiImpl.getHomeReuses()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Reuse>>)
        assert(results[1] is DgfrCallState.Success<List<Reuse>>)
    }

    // endregion getHomeReuses

    // region putSetHomeReuses

    @Test
    fun `putSetHomeReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putSetHomeReuses(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Reuse>>)
        assert(results[1] is DgfrCallState.ClientError<List<Reuse>>)
    }

    @Test
    fun `putSetHomeReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Reuse>())

        val flow = apiImpl.putSetHomeReuses(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Reuse>>)
        assert(results[1] is DgfrCallState.ServerError<List<Reuse>>)
    }

    @Test
    fun `putSetHomeReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Reuse>())

        val flow = apiImpl.putSetHomeReuses(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Reuse>>)
        assert(results[1] is DgfrCallState.Success<List<Reuse>>)
    }

    // endregion putSetHomeReuses

    // region getSuggestTerritory

    @Test
    fun `getSuggestTerritory when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestTerritory("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Territory>>)
        assert(results[1] is DgfrCallState.ClientError<List<Territory>>)
    }

    @Test
    fun `getSuggestTerritory when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Territory>())

        val flow = apiImpl.getSuggestTerritory("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Territory>>)
        assert(results[1] is DgfrCallState.ServerError<List<Territory>>)
    }

    @Test
    fun `getSuggestTerritory when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Territory>())

        val flow = apiImpl.getSuggestTerritory("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Territory>>)
        assert(results[1] is DgfrCallState.Success<List<Territory>>)
    }

    // endregion getSuggestTerritory
}
