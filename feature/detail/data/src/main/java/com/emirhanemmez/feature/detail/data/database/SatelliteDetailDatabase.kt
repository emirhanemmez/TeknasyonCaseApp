package com.emirhanemmez.feature.detail.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirhanemmez.feature.detail.data.dao.SatelliteDetailDao
import com.emirhanemmez.feature.detail.data.entity.SatelliteDetailDbEntity

@Database(entities = [SatelliteDetailDbEntity::class], version = 1, exportSchema = false)
abstract class SatelliteDetailDatabase: RoomDatabase() {
    abstract fun satelliteDetailDao(): SatelliteDetailDao
}