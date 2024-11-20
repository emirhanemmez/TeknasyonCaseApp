package com.emirhanemmez.feature.detail.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GetSatellitePositionsEntity(
    val list: List<SatellitePositionEntity>
)
