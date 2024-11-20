package com.emirhanemmez.feature.detail.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SatelliteDetailEntity(
    val id: Int,
    @SerialName("cost_per_launch") val costPerLaunch: Long,
    @SerialName("first_flight") val firstFlight: String,
    val height: Long,
    val mass: Long,
)
