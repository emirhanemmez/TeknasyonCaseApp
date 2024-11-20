package com.emirhanemmez.feature.detail.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("satellite_detail")
data class SatelliteDetailDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("cost_per_launch") val costPerLaunch: Long,
    @ColumnInfo("first_flight") val firstFlight: String,
    val height: Long,
    val mass: Long
)
