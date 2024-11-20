package com.emirhanemmez.feature.detail.presentation

import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import com.emirhanemmez.feature.detail.domain.usecase.GetSatelliteDetail
import com.emirhanemmez.feature.detail.domain.usecase.GetSatelliteLastPosition
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest : FreeSpec({
    val detailRepository = mockk<DetailRepository>()
    val mockGetSatelliteDetail = GetSatelliteDetail(detailRepository)
    val mockGetSatelliteLastPosition = GetSatelliteLastPosition(detailRepository)

    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }

    val satelliteId = 1
    val mockSatelliteDetail = SatelliteDetail(
        id = satelliteId,
        costPerLaunch = 100,
        firstFlight = "",
        height = 10,
        mass = 50
    )
    val mockPosition = Position(posX = 10.0, posY = 20.0)

    coEvery {
        mockGetSatelliteDetail.invoke(satelliteId)
    } returns flowOf(mockSatelliteDetail)

    coEvery {
        mockGetSatelliteLastPosition.invoke(satelliteId)
    } returns flowOf(mockPosition)

    "DetailViewModel" - {
        "should emit Loading state initially" {
            coEvery { mockGetSatelliteDetail.invoke(satelliteId) } returns flowOf(
                mockSatelliteDetail
            )
            coEvery { mockGetSatelliteLastPosition.invoke(satelliteId) } returns flowOf(mockPosition)

            val viewModel =
                DetailViewModel(satelliteId, mockGetSatelliteDetail, mockGetSatelliteLastPosition)

            viewModel.satelliteDetailState shouldBe DetailViewModel.SatelliteDetailState.Loading
            viewModel.positionState shouldBe DetailViewModel.PositionState.Loading
        }

        "should emit Success state when satellite detail and position are fetched successfully" {
            coEvery { mockGetSatelliteDetail.invoke(satelliteId) } returns flowOf(
                mockSatelliteDetail
            )
            coEvery { mockGetSatelliteLastPosition.invoke(satelliteId) } returns flowOf(mockPosition)

            val viewModel =
                DetailViewModel(satelliteId, mockGetSatelliteDetail, mockGetSatelliteLastPosition)

            runTest(testDispatcher) {
                advanceUntilIdle()
                viewModel.satelliteDetailState shouldBe DetailViewModel.SatelliteDetailState.Success(
                    mockSatelliteDetail
                )
                viewModel.positionState shouldBe DetailViewModel.PositionState.Success(mockPosition)
            }
        }

        "should emit Error state when satellite detail fetch fails" {
            coEvery { mockGetSatelliteDetail.invoke(satelliteId) } returns flow { throw Exception("Error fetching satellite details") }
            coEvery { mockGetSatelliteLastPosition.invoke(satelliteId) } returns flowOf(mockPosition)

            val viewModel =
                DetailViewModel(satelliteId, mockGetSatelliteDetail, mockGetSatelliteLastPosition)

            runTest(testDispatcher) {
                advanceUntilIdle()
                viewModel.satelliteDetailState shouldBe DetailViewModel.SatelliteDetailState.Error("Error fetching satellite details")
            }
        }

        "should emit Error state when position fetch fails" {
            coEvery { mockGetSatelliteDetail.invoke(satelliteId) } returns flowOf(
                mockSatelliteDetail
            )
            coEvery { mockGetSatelliteLastPosition.invoke(satelliteId) } returns flow {
                throw Exception(
                    "Error fetching satellite position"
                )
            }

            val viewModel =
                DetailViewModel(satelliteId, mockGetSatelliteDetail, mockGetSatelliteLastPosition)

            runTest(testDispatcher) {
                advanceUntilIdle()
                viewModel.positionState shouldBe DetailViewModel.PositionState.Error("Error fetching satellite position")
            }
        }
    }
})