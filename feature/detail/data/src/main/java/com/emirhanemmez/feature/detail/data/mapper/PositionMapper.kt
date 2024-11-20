package com.emirhanemmez.feature.detail.data.mapper

import com.emirhanemmez.feature.detail.data.model.PositionEntity
import com.emirhanemmez.feature.detail.domain.model.Position

object PositionMapper {
    fun mapToDomain(entity: PositionEntity): Position {
        return Position(
            posX = entity.posX,
            posY = entity.posY
        )
    }
}