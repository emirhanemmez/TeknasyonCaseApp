package com.emirhanemmez.feature.detail.data.mapper

import com.emirhanemmez.feature.detail.data.entity.SatelliteDetailDbEntity
import com.emirhanemmez.feature.detail.data.model.SatelliteDetailEntity
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class SatelliteDetailMapperTest : FreeSpec({
    "when entityToDomain is called" - {
        val entity = SatelliteDetailEntity(
            id = 1,
            costPerLaunch = 10000,
            firstFlight = "2010-06-04",
            height = 700,
            mass = 700,
        )

        val result = SatelliteDetailMapper.entityToDomain(entity)

        "then it should return SatelliteDetail" {
            result shouldBe SatelliteDetail(
                id = entity.id,
                costPerLaunch = entity.costPerLaunch,
                firstFlight = entity.firstFlight,
                height = entity.height,
                mass = entity.mass
            )
        }
    }

    "when dbEntityToDomain is called" - {
        val dbEntity = SatelliteDetailDbEntity(
            id = 1,
            costPerLaunch = 10000,
            firstFlight = "2010-06-04",
            height = 700,
            mass = 700,
        )

        val result = SatelliteDetailMapper.dbEntityToDomain(dbEntity)

        "then it should return SatelliteDetail" {
            result shouldBe SatelliteDetail(
                id = dbEntity.id,
                costPerLaunch = dbEntity.costPerLaunch,
                firstFlight = dbEntity.firstFlight,
                height = dbEntity.height,
                mass = dbEntity.mass
            )
        }
    }

    "when entityToDbEntity is called" - {
        val entity = SatelliteDetailEntity(
            id = 1,
            costPerLaunch = 10000,
            firstFlight = "2010-06-04",
            height = 700,
            mass = 700,
        )

        val result = SatelliteDetailMapper.entityToDbEntity(entity)

        "then it should return SatelliteDetailDbEntity" {
            result shouldBe SatelliteDetailDbEntity(
                id = entity.id,
                costPerLaunch = entity.costPerLaunch,
                firstFlight = entity.firstFlight,
                height = entity.height,
                mass = entity.mass
            )
        }
    }
})