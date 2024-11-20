package com.emirhanemmez.feature.list.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.emirhanemmez.feature.list.domain.model.Satellite
import org.junit.Rule
import org.junit.Test

class ListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun satelliteList_isDisplayedCorrectly() {
        val mockSatellites = listOf(
            Satellite(id = 1, name = "Satellite 1", active = true),
            Satellite(id = 2, name = "Satellite 2", active = false)
        )

        composeTestRule.setContent {
            ListScreenContent(
                screenState = ListViewModel.ScreenState.Success(mockSatellites),
                onSearch = {},
                navigateToDetail = { _, _ -> }
            )
        }

        composeTestRule.onNodeWithText("Satellite 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Satellite 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Active").assertIsDisplayed()
        composeTestRule.onNodeWithText("Passive").assertIsDisplayed()
    }

    @Test
    fun clickingSatelliteItem_navigatesToDetailScreen() {
        val mockSatellites = listOf(
            Satellite(id = 1, active = true, name = "Satellite 1"),
            Satellite(id = 2, active = false, name = "Satellite 2")
        )
        var clickedSatelliteId: Int? = null
        var clickedSatelliteName: String? = null

        composeTestRule.setContent {
            ListScreenContent(
                screenState = ListViewModel.ScreenState.Success(mockSatellites),
                onSearch = {},
                navigateToDetail = { id, name ->
                    clickedSatelliteId = id
                    clickedSatelliteName = name
                }
            )
        }

        composeTestRule.onNodeWithText("Satellite 1").performClick()

        assert(clickedSatelliteId == 1)
        assert(clickedSatelliteName == "Satellite 1")
    }
}