package com.emirhanemmez.common.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    hint: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource),
        value = searchText,
        onValueChange = {
            searchText = it
            onQueryChange(it)
        },
        interactionSource = interactionSource,
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.bodyMedium,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = searchText,
                shape = CircleShape,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable {
                                searchText = ""
                                onQueryChange("")
                            },
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clear Search",
                            tint = Color.Black.copy(alpha = 0.5f)
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                    focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                    focusedTrailingIconColor = Color.Black.copy(alpha = 0.5f),
                    unfocusedTrailingIconColor = Color.Black.copy(alpha = 0.5f),
                    unfocusedLeadingIconColor = Color.Black.copy(alpha = 0.5f),
                    focusedLeadingIconColor = Color.Black.copy(alpha = 0.5f)
                ),
                contentPadding = PaddingValues(0.dp)
            )
        }
    )
}