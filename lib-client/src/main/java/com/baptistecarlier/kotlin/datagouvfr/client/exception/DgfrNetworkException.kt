package com.baptistecarlier.kotlin.datagouvfr.client.exception

import io.ktor.client.statement.*
import io.ktor.http.*

class DgfrNetworkException(exceptionResponse: HttpResponse, exceptionResponseStatus: HttpStatusCode) : Exception()