package com.emirhanemmez.feature.detail.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val satelliteName = "Satellite1"

    @Test
    fun loadingState_showsLoader() {
        composeTestRule.setContent {
            DetailScreenContent(
                satelliteName = satelliteName,
                satelliteDetailState = DetailViewModel.SatelliteDetailState.Loading,
                positionState = DetailViewModel.PositionState.Loading,
                onRefreshDetailClick = {},
                onRefreshPositionClick = {}
            )
        }

        composeTestRule.onNodeWithTag("LoaderView").assertIsDisplayed()
    }

    @Test
    fun errorState_showsRetryView() {
        composeTestRule.setContent {
            DetailScreenContent(
                satelliteName = satelliteName,
                satelliteDetailState = DetailViewModel.SatelliteDetailState.Error("Error loading details"),
                positionState = DetailViewModel.PositionState.Error("Error loading position"),
                onRefreshDetailClick = {},
                onRefreshPositionClick = {}
            )
        }

        composeTestRule.onNodeWithText("Error loading details").assertIsDisplayed()
    }

    @Test
    fun successState_displaysSatelliteDetails() {
        val testSatelliteDetail = SatelliteDetail(
            id = 1,
            firstFlight = "2024-11-19",
            height = 50,
            mass = 500,
            costPerLaunch = 100_000
        )

        composeTestRule.setContent {
            DetailScreenContent(
                satelliteName = satelliteName,
                satelliteDetailState = DetailViewModel.SatelliteDetailState.Success(
                    testSatelliteDetail
                ),
                positionState = DetailViewModel.PositionState.Success(
                    lastPosition = Position(posX = 10.0, posY = 20.0)
                ),
                onRefreshDetailClick = {},
                onRefreshPositionClick = {}
            )
        }

        composeTestRule.onNodeWithText(satelliteName).assertIsDisplayed()
        composeTestRule.onNodeWithText("2024-11-19").assertIsDisplayed()
    }
}