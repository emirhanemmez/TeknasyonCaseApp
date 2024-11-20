package com.emirhanemmez.common.presentation.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class DarkModeValuesProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(false, true)
}