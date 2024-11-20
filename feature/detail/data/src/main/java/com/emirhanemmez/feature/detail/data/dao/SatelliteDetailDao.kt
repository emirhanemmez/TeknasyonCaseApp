package com.emirhanemmez.feature.detail.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emirhanemmez.feature.detail.data.entity.SatelliteDetailDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDetailDao {
    @Query("SELECT * FROM satellite_detail WHERE id = :satelliteId")
    fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetailDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDbEntity)
}