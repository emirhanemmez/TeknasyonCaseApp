package com.emirhanemmez.feature.detail.data.mapper

import com.emirhanemmez.feature.detail.data.model.PositionEntity
import com.emirhanemmez.feature.detail.domain.model.Position
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PositionMapperTest : FreeSpec({
    val entity = PositionEntity(
        posX = 100.0,
        posY = 100.0
    )

    "when mapToDomain is called" - {
        val result = PositionMapper.mapToDomain(entity)

        "then it should return Position" {
            result shouldBe Position(
                posX = entity.posX,
                posY = entity.posY
            )
        }
    }
})