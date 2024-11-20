package com.emirhanemmez.feature.detail.data.mapper

import com.emirhanemmez.feature.detail.data.entity.SatelliteDetailDbEntity
import com.emirhanemmez.feature.detail.data.model.SatelliteDetailEntity
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail

object SatelliteDetailMapper {
    fun entityToDomain(entity: SatelliteDetailEntity): SatelliteDetail {
        return SatelliteDetail(
            id = entity.id,
            costPerLaunch = entity.costPerLaunch,
            firstFlight = entity.firstFlight,
            height = entity.height,
            mass = entity.mass
        )
    }

    fun dbEntityToDomain(dbEntity: SatelliteDetailDbEntity): SatelliteDetail {
        return SatelliteDetail(
            id = dbEntity.id,
            costPerLaunch = dbEntity.costPerLaunch,
            firstFlight = dbEntity.firstFlight,
            height = dbEntity.height,
            mass = dbEntity.mass
        )
    }

    fun entityToDbEntity(entity: SatelliteDetailEntity): SatelliteDetailDbEntity {
        return SatelliteDetailDbEntity(
            id = entity.id,
            costPerLaunch = entity.costPerLaunch,
            firstFlight = entity.firstFlight,
            height = entity.height,
            mass = entity.mass
        )
    }
}