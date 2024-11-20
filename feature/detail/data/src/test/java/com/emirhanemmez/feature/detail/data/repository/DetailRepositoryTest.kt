package com.emirhanemmez.feature.detail.data.repository

import android.content.Context
import android.content.res.Resources
import com.emirhanemmez.feature.detail.data.R
import com.emirhanemmez.feature.detail.data.dao.SatelliteDetailDao
import com.emirhanemmez.feature.detail.data.entity.SatelliteDetailDbEntity
import com.emirhanemmez.feature.detail.data.model.GetSatellitePositionsEntity
import com.emirhanemmez.feature.detail.data.model.PositionEntity
import com.emirhanemmez.feature.detail.data.model.SatelliteDetailEntity
import com.emirhanemmez.feature.detail.data.model.SatellitePositionEntity
import com.emirhanemmez.feature.detail.data.service.DetailService
import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DetailRepositoryTest : FreeSpec({
    val context = mockk<Context>()
    val resources = mockk<Resources>()
    val satelliteDetailDao = mockk<SatelliteDetailDao>()
    val detailService = spyk(DetailService(context))
    val sut = DetailRepositoryImpl(detailService, satelliteDetailDao, Dispatchers.IO)

    val satelliteDetailEntity = SatelliteDetailEntity(
        id = 1,
        costPerLaunch = 10000,
        firstFlight = "2010-06-04",
        height = 700,
        mass = 700
    )

    val positionEntity = PositionEntity(
        posX = 0.864328541,
        posY = 0.646450811
    )

    every { context.resources } returns resources
    every { resources.openRawResource(R.raw.satellite_detail) } returns
            Json.encodeToString(listOf(satelliteDetailEntity))
                .trimIndent()
                .byteInputStream()
    every { resources.openRawResource(R.raw.positions) } returns
            Json.encodeToString(
                GetSatellitePositionsEntity(
                    list = listOf(
                        SatellitePositionEntity(
                            id = 1,
                            positions = listOf(positionEntity)
                        )
                    ),
                )
            ).trimIndent()
                .byteInputStream()

    coEvery {
        satelliteDetailDao.getSatelliteDetail(1)
    } returns flowOf(
        SatelliteDetailDbEntity(
            id = satelliteDetailEntity.id,
            costPerLaunch = satelliteDetailEntity.costPerLaunch,
            firstFlight = satelliteDetailEntity.firstFlight,
            height = satelliteDetailEntity.height,
            mass = satelliteDetailEntity.mass
        )
    )

    "when getSatelliteDetail called" - {
        val result = sut.getSatelliteDetail(1).first()

        "then it should return satellite detail" {
            result shouldBe SatelliteDetail(
                id = satelliteDetailEntity.id,
                costPerLaunch = satelliteDetailEntity.costPerLaunch,
                firstFlight = satelliteDetailEntity.firstFlight,
                height = satelliteDetailEntity.height,
                mass = satelliteDetailEntity.mass
            )
        }
    }

    "when getSatelliteLastPosition called" - {
        val result = sut.getSatelliteLastPosition(1).first()

        "then it should return satellite last position" {
            result shouldBe Position(
                posX = positionEntity.posX,
                posY = positionEntity.posY
            )
        }
    }
})