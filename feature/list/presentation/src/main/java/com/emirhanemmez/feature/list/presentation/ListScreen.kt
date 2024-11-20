package com.emirhanemmez.feature.list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhanemmez.common.presentation.composable.DarkModeValuesProvider
import com.emirhanemmez.common.presentation.composable.LoaderView
import com.emirhanemmez.common.presentation.composable.MySearchBar
import com.emirhanemmez.common.presentation.composable.RetryView
import com.emirhanemmez.common.presentation.ui.theme.TeknasyonCaseTheme
import com.emirhanemmez.common.presentation.util.ext.clearFocusOnTap
import com.emirhanemmez.feature.list.domain.model.Satellite

@Composable
fun ListScreen(
    navigateToDetail: (id: Int, name: String) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    ListScreenContent(
        screenState = viewModel.screenState,
        onSearch = viewModel::onSearch,
        navigateToDetail = { id, name ->
            viewModel.getSatellites("")
            navigateToDetail.invoke(id, name)
        }
    )
}

@Composable
fun ListScreenContent(
    screenState: ListViewModel.ScreenState,
    onSearch: (String) -> Unit,
    navigateToDetail: (id: Int, name: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnTap()
    ) {
        MySearchBar(
            hint = "Search",
            onQueryChange = onSearch,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        when (screenState) {
            ListViewModel.ScreenState.Loading -> {
                LoaderView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }

            is ListViewModel.ScreenState.Success -> {
                LazyColumn {
                    itemsIndexed(
                        items = screenState.satelliteList,
                        key = { _, it -> it.id }
                    ) { index, it ->
                        SatelliteListItem(
                            satellite = it,
                            navigateToDetail = navigateToDetail
                        )

                        if (index != screenState.satelliteList.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }

            is ListViewModel.ScreenState.Error ->
                RetryView(
                    errorMessage = screenState.errorMessage,
                    onRefreshClick = { onSearch("") }
                )
        }
    }
}

@Composable
private fun SatelliteListItem(
    satellite: Satellite,
    navigateToDetail: (id: Int, name: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navigateToDetail(satellite.id, satellite.name)
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = if (satellite.active)
                        Color.Green
                    else
                        Color.Red,
                    shape = CircleShape
                ),
        )

        Spacer(modifier = Modifier.width(32.dp))

        Column {
            Text(
                text = satellite.name
            )

            Text(
                text = if (satellite.active)
                    "Active"
                else
                    "Passive"
            )
        }
    }
}

@Preview
@Composable
private fun ListScreenContentPreview(
    @PreviewParameter(DarkModeValuesProvider::class) darkMode: Boolean
) {
    TeknasyonCaseTheme(darkTheme = darkMode) {
        ListScreenContent(
            screenState = ListViewModel.ScreenState.Success(
                listOf(
                    Satellite(
                        id = 1,
                        active = true,
                        name = "Satellite 1"
                    ),
                    Satellite(
                        id = 2,
                        active = false,
                        name = "Satellite 2"
                    )
                )
            ),
            onSearch = {},
            navigateToDetail = { _, _ -> }
        )
    }
}
