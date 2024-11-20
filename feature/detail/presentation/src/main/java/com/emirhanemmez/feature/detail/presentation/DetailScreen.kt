package com.emirhanemmez.feature.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhanemmez.common.presentation.composable.LoaderView
import com.emirhanemmez.common.presentation.composable.RetryView
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail

@Composable
fun DetailScreen(
    satelliteId: Int,
    satelliteName: String,
    viewModel: DetailViewModel = hiltViewModel(
        creationCallback = { factory: DetailViewModel.Factory ->
            factory.create(satelliteId)
        }
    )
) {
    DetailScreenContent(
        satelliteName = satelliteName,
        satelliteDetailState = viewModel.satelliteDetailState,
        positionState = viewModel.positionState,
        onRefreshDetailClick = { viewModel.getSatelliteDetail() },
        onRefreshPositionClick = { viewModel.getSatelliteLastPosition() }
    )
}

@Composable
fun DetailScreenContent(
    satelliteName: String,
    satelliteDetailState: DetailViewModel.SatelliteDetailState,
    positionState: DetailViewModel.PositionState,
    onRefreshDetailClick: () -> Unit,
    onRefreshPositionClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (satelliteDetailState) {
            is DetailViewModel.SatelliteDetailState.Loading -> {
                LoaderView(Modifier.testTag("LoaderView"))
            }

            is DetailViewModel.SatelliteDetailState.Success -> {
                DetailItem(
                    satelliteName = satelliteName,
                    satelliteDetail = satelliteDetailState.satelliteDetail,
                    positionState = positionState,
                    onRefreshPositionClick = onRefreshPositionClick
                )
            }

            is DetailViewModel.SatelliteDetailState.Error -> {
                RetryView(
                    errorMessage = satelliteDetailState.message,
                    onRefreshClick = onRefreshDetailClick
                )
            }
        }
    }
}

@Composable
private fun DetailItem(
    satelliteName: String,
    satelliteDetail: SatelliteDetail,
    positionState: DetailViewModel.PositionState,
    onRefreshPositionClick: () -> Unit
) {
    val heightMassString = "${satelliteDetail.height}/${satelliteDetail.mass}"

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = satelliteName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(text = satelliteDetail.firstFlight)

        Spacer(Modifier.height(48.dp))

        Text(text = boldAnnotatedString("Height/Mass: ", heightMassString))

        Spacer(Modifier.height(8.dp))

        Text(
            text = boldAnnotatedString(
                "Cost per launch: ",
                satelliteDetail.costPerLaunch.toString()
            )
        )

        Spacer(Modifier.height(8.dp))
        when (positionState) {
            is DetailViewModel.PositionState.Loading -> {
                LoaderView()
            }

            is DetailViewModel.PositionState.Success -> {
                Text(
                    text = boldAnnotatedString(
                        "Last Position: ",
                        "(${positionState.lastPosition.posX}, ${positionState.lastPosition.posY})"
                    )
                )
            }

            is DetailViewModel.PositionState.Error -> {
                RetryView(
                    errorMessage = positionState.message,
                    onRefreshClick = onRefreshPositionClick
                )
            }
        }
    }
}

@Composable
private fun boldAnnotatedString(
    boldPart: String,
    valuePart: String
) = buildAnnotatedString {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append(boldPart)
    }

    append(" ")

    append(valuePart)
}