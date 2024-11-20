package com.emirhanemmez.feature.list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhanemmez.feature.list.domain.model.Satellite
import com.emirhanemmez.feature.list.domain.usecase.GetSatellites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getSatellites: GetSatellites
) : ViewModel() {
    var screenState by mutableStateOf<ScreenState>(ScreenState.Loading)
        private set

    init {
        getSatellites("")
    }

    @OptIn(FlowPreview::class)
    fun getSatellites(query: String) {
        viewModelScope.launch {
            getSatellites.invoke(query)
                .debounce(200)
                .distinctUntilChanged()
                .onStart {
                    screenState = ScreenState.Loading
                    delay(1000)
                }
                .catch {
                    screenState = ScreenState.Error(it.message ?: "An error occurred")
                }
                .collect {
                    screenState = ScreenState.Success(it)
                }
        }
    }

    fun onSearch(query: String) {
        if (query.isNotEmpty() && query.length > 1) {
            getSatellites(query)
        }
    }

    sealed class ScreenState {
        data object Loading : ScreenState()
        data class Success(val satelliteList: List<Satellite>) : ScreenState()
        data class Error(val errorMessage: String) : ScreenState()
    }
}