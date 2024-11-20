package com.emirhanemmez.feature.list.data.mapper

import com.emirhanemmez.feature.list.domain.model.Satellite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class SatelliteMapperTest : FreeSpec({
    "when mapToDomain called" - {
        val entity = com.emirhanemmez.feature.list.data.model.SatelliteEntity(
            id = 1,
            name = "Satellite",
            active = true
        )

        val result = SatelliteMapper.mapToDomain(entity)

        "then it should return Satellite" {
            result shouldBe Satellite(
                id = 1,
                name = "Satellite",
                active = true
            )
        }
    }
})