package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.exception.ClientErrorCode

/**
 * Class used as library return.
 * They will be emitted from [Flow].
 */
sealed class DgfrCallState<T> {
    class Loading<T>() : DgfrCallState<T>()
    class ClientError<T>(val cec: ClientErrorCode) : DgfrCallState<T>()
    class ServerError<T>(val httpCode: Int, val message: String? = null) : DgfrCallState<T>()
    class Success<T>(val data: T) : DgfrCallState<T>()
}
