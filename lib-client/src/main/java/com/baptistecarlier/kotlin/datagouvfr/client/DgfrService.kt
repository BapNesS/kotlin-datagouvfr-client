package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.DatasetsApi
import com.baptistecarlier.kotlin.datagouvfr.client.api.DatasetsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.api.MeApi
import com.baptistecarlier.kotlin.datagouvfr.client.api.MeApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.logger.DgfrHttpLogger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*


private val httpClient: HttpClient by lazy {
    HttpClient(CIO) {
        val timeOut: Long = 60_000
        val host = "www.data.gouv.fr"
        val basePath = "/api/1/"

        this.defaultRequest {
            url.host = host
            url.protocol = URLProtocol.HTTPS
            url.encodedPath = basePath + url.encodedPath
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                requestTimeout = timeOut
            }
        }

        install(Logging) {
            logger = DgfrHttpLogger()
            level = LogLevel.ALL
        }
    }
}

private val datasetsApi by lazy { DatasetsApiImpl(httpClient) }
private val meApi by lazy { MeApiImpl(httpClient) }

class DgfrService(apiKey: String = "") :
    DatasetsApi by datasetsApi,
    MeApi by meApi
{

    init {
        setApiKey(apiKey)
    }

    override fun setApiKey(apiKey: String) {
        meApi.setApiKey(apiKey)
    }

}
