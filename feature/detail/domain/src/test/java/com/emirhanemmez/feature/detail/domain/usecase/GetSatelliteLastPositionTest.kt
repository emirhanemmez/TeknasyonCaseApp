package com.emirhanemmez.feature.detail.domain.usecase

import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class GetSatelliteLastPositionTest : FreeSpec({
    val detailRepository = mockk<DetailRepository>()
    val sut = GetSatelliteLastPosition(detailRepository)

    "when invoke called" - {
        val position = Position(
            posX = 100.0,
            posY = 100.0
        )

        coEvery {
            detailRepository.getSatelliteLastPosition(1)
        } returns flowOf(position)

        val result = sut.invoke(1).first()

        "then it should return position" {
            result shouldBe position
        }
    }
})