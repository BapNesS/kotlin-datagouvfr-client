package com.baptistecarlier.kotlin.datagouvfr.client.exception

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import kotlinx.coroutines.flow.flow

internal inline fun <reified T> loadingFlow(crossinline ktorCall: suspend () -> T) = flow {
    emit(DgfrResource.Loading())
    emit(
        exceptionalHandledCall {
            ktorCall.invoke()
        }
    )
}

internal suspend inline fun <reified T> exceptionalHandledCall(ktorCall: suspend () -> T): DgfrResource<T> {
    return try {
        val success = ktorCall.invoke()
        DgfrResource.Success(success)

    } catch (dgfrException: DgfrException) {
        DgfrResource.ServerError(dgfrException.httpCode)

    } catch (e: Exception) {
        DgfrResource.ClientError(ClientErrorCode.UNKNOWN)
    }
}