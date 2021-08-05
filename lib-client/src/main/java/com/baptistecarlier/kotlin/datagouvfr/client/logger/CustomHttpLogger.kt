package com.baptistecarlier.kotlin.datagouvfr.client.logger

import android.util.Log
import io.ktor.client.features.logging.*

internal class DgfrHttpLogger : Logger {
    private val tag = "DgfrHttpLogger"
    override fun log(message: String) {
        Log.i(tag, message)
    }
}