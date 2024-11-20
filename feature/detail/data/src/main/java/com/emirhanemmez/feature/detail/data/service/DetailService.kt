package com.emirhanemmez.feature.detail.data.service

import android.content.Context
import com.emirhanemmez.feature.detail.data.R
import com.emirhanemmez.feature.detail.data.model.GetSatellitePositionsEntity
import com.emirhanemmez.feature.detail.data.model.PositionEntity
import com.emirhanemmez.feature.detail.data.model.SatelliteDetailEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DetailService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getSatelliteDetail(satelliteId: Int): SatelliteDetailEntity {
        val jsonString = context.resources.openRawResource(R.raw.satellite_detail)
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString<List<SatelliteDetailEntity>>(jsonString)
            .first { it.id == satelliteId }
    }

    fun getSatelliteLastPosition(satelliteId: Int): PositionEntity {
        val jsonString = context.resources.openRawResource(R.raw.positions)
            .bufferedReader()
            .use { it.readText() }

        val getSatellitePositionsEntity =
            Json.decodeFromString<GetSatellitePositionsEntity>(jsonString)

        return getSatellitePositionsEntity.list
            .first { it.id == satelliteId }
            .positions
            .random()
    }
}