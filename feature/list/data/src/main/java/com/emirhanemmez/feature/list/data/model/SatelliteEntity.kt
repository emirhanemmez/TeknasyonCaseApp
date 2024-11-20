package com.emirhanemmez.feature.list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SatelliteEntity(
    val id: Int,
    val name: String,
    val active: Boolean
)
