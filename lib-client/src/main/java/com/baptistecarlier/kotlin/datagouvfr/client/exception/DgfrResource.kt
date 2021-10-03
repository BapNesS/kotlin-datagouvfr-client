package com.baptistecarlier.kotlin.datagouvfr.client.exception

sealed class DgfrResource<T> {
    class Success<T>(val data: T) : DgfrResource<T>()
    class ClientError<T>(val cec: ClientErrorCode) : DgfrResource<T>()
    class ServerError<T>(val httpCode: Int, val message: String? = null) : DgfrResource<T>()
    class Loading<T>() : DgfrResource<T>()
}

enum class ClientErrorCode {
    EMPTY_API_KEY, // Not used yet
    UNKNOWN,
}