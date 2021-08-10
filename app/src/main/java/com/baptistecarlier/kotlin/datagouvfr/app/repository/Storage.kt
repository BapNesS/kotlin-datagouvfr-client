package com.baptistecarlier.kotlin.datagouvfr.app.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.settings: DataStore<Preferences> by preferencesDataStore(name = "settings")

class Storage(context: Context) {

    companion object {
        val API_KEY : Preferences.Key<String> = stringPreferencesKey("API_KEY")
    }

    private val settings: DataStore<Preferences> = context.settings

    suspend fun updateApiKey(newValue: String) {
        settings.edit {
            it[API_KEY] = newValue
        }
    }

    val apiKeyFlow: Flow<String> = settings.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[API_KEY].orEmpty()
    }

}

