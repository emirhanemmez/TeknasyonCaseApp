package com.emirhanemmez.feature.detail.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SatellitePositionEntity(
    val id: Int,
    val positions: List<PositionEntity>
)
