package com.emirhanemmez.common.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RetryView(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRefreshClick: () -> Unit,
) {
    Column(
        modifier = modifier.clickable {
            onRefreshClick.invoke()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Refresh Icon",
        )
    }
}