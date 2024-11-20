package com.emirhanemmez.feature.list.data.service

import android.content.Context
import com.emirhanemmez.feature.list.data.R
import com.emirhanemmez.feature.list.data.model.SatelliteEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ListService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getSatellites(query: String): List<SatelliteEntity> {
        val jsonString = context.resources.openRawResource(R.raw.satellite_list)
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString<List<SatelliteEntity>>(jsonString)
            .filter { it.name.contains(query, ignoreCase = true) }
    }
}