package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.exception.ClientErrorCode

/**
 * Class used as library return.
 * They will be emitted from [Flow].
 */
sealed class DgfrResource<T> {
    class Loading<T>() : DgfrResource<T>()
    class ClientError<T>(val cec: ClientErrorCode) : DgfrResource<T>()
    class ServerError<T>(val httpCode: Int, val message: String? = null) : DgfrResource<T>()
    class Success<T>(val data: T) : DgfrResource<T>()
}
