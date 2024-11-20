package com.emirhanemmez.feature.detail.domain.usecase

import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class GetSatelliteDetailTest : FreeSpec({
    val detailRepository = mockk<DetailRepository>()
    val sut = GetSatelliteDetail(detailRepository)

    "when invoke called" - {
        val satelliteDetail = SatelliteDetail(
            id = 1,
            costPerLaunch = 100,
            firstFlight = "2022-01-01",
            height = 100,
            mass = 100
        )

        coEvery {
            detailRepository.getSatelliteDetail(1)
        } returns flowOf(satelliteDetail)

        val result = sut.invoke(1).first()

        "then it should return satellite detail" {
            result shouldBe satelliteDetail
        }
    }
})
