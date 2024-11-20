package com.emirhanemmez.feature.detail.domain.repository

import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetail>
    fun getSatelliteLastPosition(satelliteId: Int): Flow<Position>
}