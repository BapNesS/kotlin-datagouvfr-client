package com.baptistecarlier.kotlin.datagouvfr.client.mock

import com.baptistecarlier.kotlin.datagouvfr.client.validators
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal val mockResponseHeaders =
    headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

internal class ApiMockEngine {

    internal inline operator fun <reified T> invoke(
        httpStatusCode: HttpStatusCode,
        response: T?
    ): HttpClient =
        HttpClient(MockEngine) {
            engine {
                addHandler { request ->
                    if (request.method != HttpMethod.Get && request.headers.contains("X-API-KEY").not()) {
                        val status = HttpStatusCode.Unauthorized
                        respond(status.toString(), status, mockResponseHeaders)
                    } else if ((
                        request.url.fullPath.startsWith("https://localhost/me") ||
                            request.url.fullPath.startsWith("https://localhost/notifications")
                        ) && request.headers.contains("X-API-KEY").not()
                    ) {
                        val status = HttpStatusCode.Unauthorized
                        respond(status.toString(), status, mockResponseHeaders)
                    } else {
                        add(httpStatusCode, response)
                    }
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            validators()
        }

    internal fun error(): HttpClient = HttpClient() {
        validators()
    }

    inline fun <reified T> MockRequestHandleScope.add(
        httpStatusCode: HttpStatusCode,
        response: T?
    ): HttpResponseData {
        return when (httpStatusCode) {
            HttpStatusCode.OK -> {
                respond(Json.encodeToString(response), httpStatusCode, mockResponseHeaders)
            }
            else -> {
                respond(httpStatusCode.toString(), httpStatusCode, mockResponseHeaders)
            }
        }
    }
}
