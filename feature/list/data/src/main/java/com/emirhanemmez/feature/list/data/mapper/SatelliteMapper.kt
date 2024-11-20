package com.emirhanemmez.feature.list.data.mapper

import com.emirhanemmez.feature.list.domain.model.Satellite

object SatelliteMapper {
    fun mapToDomain(entity: com.emirhanemmez.feature.list.data.model.SatelliteEntity): Satellite {
        return Satellite(
            id = entity.id,
            name = entity.name,
            active = entity.active,
        )
    }
}