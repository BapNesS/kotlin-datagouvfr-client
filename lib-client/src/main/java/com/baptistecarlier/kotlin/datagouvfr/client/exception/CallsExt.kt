package com.baptistecarlier.kotlin.datagouvfr.client.exception

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import kotlinx.coroutines.flow.flow

internal inline fun <reified T> loadingFlow(crossinline ktorCall: suspend () -> T) = flow {
    emit(DgfrCallState.Loading())
    emit(
        exceptionalHandledCall {
            ktorCall.invoke()
        }
    )
}

internal suspend inline fun <reified T> exceptionalHandledCall(ktorCall: suspend () -> T): DgfrCallState<T> {
    return try {
        val success = ktorCall.invoke()
        DgfrCallState.Success(success)
    } catch (dgfrException: DgfrException) {
        DgfrCallState.ServerError(dgfrException.httpCode)
    } catch (e: Exception) {
        DgfrCallState.ClientError(ClientErrorCode.UNKNOWN)
    }
}
