package com.baptistecarlier.kotlin.datagouvfr.app.repository

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset

class Repository {

    @Deprecated("To remove")
    private val apiKey = ""

    suspend fun call(): Dataset? {
        Log.d("Repo", "call () ")
        val res = DgfrService(logging = true, apiKey = apiKey).listDatasets( q ="api.gouv.fr")
        Log.d("Repo", "res = $res")

        return res?.data?.first()
    }

}

