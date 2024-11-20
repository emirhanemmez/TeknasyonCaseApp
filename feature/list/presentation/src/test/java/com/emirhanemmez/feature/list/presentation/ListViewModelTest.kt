package com.emirhanemmez.feature.list.presentation

import com.emirhanemmez.feature.list.domain.model.Satellite
import com.emirhanemmez.feature.list.domain.repository.ListRepository
import com.emirhanemmez.feature.list.domain.usecase.GetSatellites
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
class ListViewModelTest : FreeSpec({
    val listRepository = mockk<ListRepository>()
    val mockGetSatellites = GetSatellites(listRepository)

    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }

    val mockSatellites = listOf(
        Satellite(id = 1, name = "Satellite1", active = true),
        Satellite(id = 2, name = "Satellite2", active = true),
        Satellite(id = 3, name = "Satellite3", active = false)
    )

    coEvery {
        listRepository.getSatellites("")
    } returns flowOf(mockSatellites)

    "ListViewModel" - {
        "should emit Loading state initially" {
            val viewModel = ListViewModel(mockGetSatellites)

            viewModel.screenState shouldBe ListViewModel.ScreenState.Loading
        }

        "should emit Success state when data is fetched successfully" {
            val viewModel = ListViewModel(mockGetSatellites)

            runTest(testDispatcher) {
                advanceUntilIdle()
                viewModel.screenState shouldBe ListViewModel.ScreenState.Success(mockSatellites)
            }
        }

        "should emit Error state when an exception occurs" {
            coEvery { mockGetSatellites.invoke(any()) } returns flow {
                throw Exception("Test error")
            }

            val viewModel = ListViewModel(mockGetSatellites)

            runTest(testDispatcher) {
                advanceUntilIdle()
                viewModel.screenState shouldBe ListViewModel.ScreenState.Error("Test error")
            }
        }

        "should debounce and fetch satellites for a query" {
            val query = "Satellite"
            coEvery { mockGetSatellites.invoke(query) } returns flowOf(
                mockSatellites.filter { it.name.contains(query, ignoreCase = true) }
            )

            val viewModel = ListViewModel(mockGetSatellites)

            runTest(testDispatcher) {
                viewModel.getSatellites(query)
                advanceUntilIdle()
                viewModel.screenState shouldBe ListViewModel.ScreenState.Success(
                    listOf(
                        Satellite(id = 1, name = "Satellite1", active = true),
                        Satellite(id = 2, name = "Satellite2", active = true),
                        Satellite(id = 3, name = "Satellite3", active = false)
                    )
                )
            }
        }
    }
})