package com.baptistecarlier.kotlin.datagouvfr.app.repository

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset

class Repository {

    suspend fun call(): Dataset? {
        Log.d("Repo", "call () ")
        val res = DgfrService(logging = true, apiKey = "").listDatasets( q ="api.gouv.fr")
        Log.d("Repo", "res = $res")

        return res?.data?.first()
    }

}

