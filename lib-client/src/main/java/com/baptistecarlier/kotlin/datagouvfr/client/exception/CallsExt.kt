package com.baptistecarlier.kotlin.datagouvfr.client.exception

import kotlinx.coroutines.flow.flow

suspend inline fun <reified T> loadingFlow(crossinline ktorCall: suspend () -> T) = flow {
    emit(DgfrResource.Loading())
    emit(
        exceptionalHandledCall {
            ktorCall.invoke()
        }
    )
}

suspend inline fun <reified T> exceptionalHandledCall(ktorCall: suspend () -> T): DgfrResource<T> {
    return try {
        val success = ktorCall.invoke()
        DgfrResource.Success(success)

    } catch (dgfrException: DgfrException) {
        DgfrResource.ServerError(dgfrException.httpCode)

    } catch (e: Exception) {
        DgfrResource.ClientError(ClientErrorCode.UNKNOWN)
    }
}