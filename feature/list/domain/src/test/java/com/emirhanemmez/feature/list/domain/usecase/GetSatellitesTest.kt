package com.emirhanemmez.feature.list.domain.usecase

import com.emirhanemmez.feature.list.domain.model.Satellite
import com.emirhanemmez.feature.list.domain.repository.ListRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class GetSatellitesTest : FreeSpec({
    val listRepository = mockk<ListRepository>()

    val sut = GetSatellites(listRepository)

    "when invoke is called" - {
        val expectedSatellites = listOf(
            Satellite(
                id = 1,
                name = "Satellite 1",
                active = true
            )
        )

        coEvery {
            listRepository.getSatellites("")
        } returns flowOf(expectedSatellites)

        val result = sut.invoke("").first()

        "verify that getSatellites is called" {
            coEvery {
                listRepository.getSatellites("")
            }
        }

        "then it should return a list of satellites" {
            result shouldBe expectedSatellites
        }
    }
})