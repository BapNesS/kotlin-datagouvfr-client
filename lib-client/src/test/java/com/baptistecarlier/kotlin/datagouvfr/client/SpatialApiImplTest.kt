package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.SpatialApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockGeoJSONFeature
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockGeoJSONFeatureCollection
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class SpatialApiImplTest {

    private lateinit var apiImpl: SpatialApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = SpatialApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = SpatialApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getSpatialCoverage

    @Test
    fun `getSpatialCoverage when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialCoverage("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoJSONFeatureCollection>>)
        assert(results[1] is DgfrResource.ClientError<List<GeoJSONFeatureCollection>>)
    }

    @Test
    fun `getSpatialCoverage when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<GeoJSONFeatureCollection>())

        val flow = apiImpl.getSpatialCoverage("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoJSONFeatureCollection>>)
        assert(results[1] is DgfrResource.ServerError<List<GeoJSONFeatureCollection>>)
    }

    @Test
    fun `getSpatialCoverage when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<GeoJSONFeatureCollection>())

        val flow = apiImpl.getSpatialCoverage("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoJSONFeatureCollection>>)
        assert(results[1] is DgfrResource.Success<List<GeoJSONFeatureCollection>>)
    }

    // endregion getSpatialCoverage

    // region getSpatialGranularities

    @Test
    fun `getSpatialGranularities when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialGranularities()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoGranularity>>)
        assert(results[1] is DgfrResource.ClientError<List<GeoGranularity>>)
    }

    @Test
    fun `getSpatialGranularities when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<GeoGranularity>())

        val flow = apiImpl.getSpatialGranularities()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoGranularity>>)
        assert(results[1] is DgfrResource.ServerError<List<GeoGranularity>>)
    }

    @Test
    fun `getSpatialGranularities when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<GeoGranularity>())

        val flow = apiImpl.getSpatialGranularities()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoGranularity>>)
        assert(results[1] is DgfrResource.Success<List<GeoGranularity>>)
    }

    // endregion getSpatialGranularities

    // region getSpatialLevels

    @Test
    fun `getSpatialLevels when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialLevels()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoLevel>>)
        assert(results[1] is DgfrResource.ClientError<List<GeoLevel>>)
    }

    @Test
    fun `getSpatialLevels when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<GeoLevel>())

        val flow = apiImpl.getSpatialLevels()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoLevel>>)
        assert(results[1] is DgfrResource.ServerError<List<GeoLevel>>)
    }

    @Test
    fun `getSpatialLevels when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<GeoLevel>())

        val flow = apiImpl.getSpatialLevels()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoLevel>>)
        assert(results[1] is DgfrResource.Success<List<GeoLevel>>)
    }

    // endregion getSpatialLevels

    // region getSpatialZone

    @Test
    fun `getSpatialZone when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialZone("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<GeoJSONFeature>)
        assert(results[1] is DgfrResource.ClientError<GeoJSONFeature>)
    }

    @Test
    fun `getSpatialZone when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockGeoJSONFeature)

        val flow = apiImpl.getSpatialZone("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<GeoJSONFeature>)
        assert(results[1] is DgfrResource.ServerError<GeoJSONFeature>)
    }

    @Test
    fun `getSpatialZone when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockGeoJSONFeature)

        val flow = apiImpl.getSpatialZone("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<GeoJSONFeature>)
        assert(results[1] is DgfrResource.Success<GeoJSONFeature>)
    }

    // endregion getSpatialZone

    // region getSpatialZoneChildren

    @Test
    fun `getSpatialZoneChildren when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialZoneChildren("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoJSONFeatureCollection>>)
        assert(results[1] is DgfrResource.ClientError<List<GeoJSONFeatureCollection>>)
    }

    @Test
    fun `getSpatialZoneChildren when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<GeoJSONFeatureCollection>())

        val flow = apiImpl.getSpatialZoneChildren("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoJSONFeatureCollection>>)
        assert(results[1] is DgfrResource.ServerError<List<GeoJSONFeatureCollection>>)
    }

    @Test
    fun `getSpatialZoneChildren when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<GeoJSONFeatureCollection>())

        val flow = apiImpl.getSpatialZoneChildren("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<GeoJSONFeatureCollection>>)
        assert(results[1] is DgfrResource.Success<List<GeoJSONFeatureCollection>>)
    }

    // endregion getSpatialZoneChildren

    // region getSpatialZoneDatasets

    @Test
    fun `getSpatialZoneDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialZoneDatasets("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<DatasetReference>>)
        assert(results[1] is DgfrResource.ClientError<List<DatasetReference>>)
    }

    @Test
    fun `getSpatialZoneDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<DatasetReference>())

        val flow = apiImpl.getSpatialZoneDatasets("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<DatasetReference>>)
        assert(results[1] is DgfrResource.ServerError<List<DatasetReference>>)
    }

    @Test
    fun `getSpatialZoneDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<DatasetReference>())

        val flow = apiImpl.getSpatialZoneDatasets("", null, null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<DatasetReference>>)
        assert(results[1] is DgfrResource.Success<List<DatasetReference>>)
    }

    // endregion getSpatialZoneDatasets

    // region getSuggestZones

    @Test
    fun `getSuggestZones when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestZones("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<TerritorySuggestion>>)
        assert(results[1] is DgfrResource.ClientError<List<TerritorySuggestion>>)
    }

    @Test
    fun `getSuggestZones when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<TerritorySuggestion>())

        val flow = apiImpl.getSuggestZones("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<TerritorySuggestion>>)
        assert(results[1] is DgfrResource.ServerError<List<TerritorySuggestion>>)
    }

    @Test
    fun `getSuggestZones when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<TerritorySuggestion>())

        val flow = apiImpl.getSuggestZones("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<TerritorySuggestion>>)
        assert(results[1] is DgfrResource.Success<List<TerritorySuggestion>>)
    }

    // endregion getSuggestZones

    // region getSpatialZones

    @Test
    fun `getSpatialZones when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSpatialZones(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<GeoJSONFeatureCollection>)
        assert(results[1] is DgfrResource.ClientError<GeoJSONFeatureCollection>)
    }

    @Test
    fun `getSpatialZones when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockGeoJSONFeatureCollection)

        val flow = apiImpl.getSpatialZones(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<GeoJSONFeatureCollection>)
        assert(results[1] is DgfrResource.ServerError<GeoJSONFeatureCollection>)
    }

    @Test
    fun `getSpatialZones when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockGeoJSONFeatureCollection)

        val flow = apiImpl.getSpatialZones(emptyList<String>())
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<GeoJSONFeatureCollection>)
        assert(results[1] is DgfrResource.Success<GeoJSONFeatureCollection>)
    }

    // endregion getSpatialZones
}
