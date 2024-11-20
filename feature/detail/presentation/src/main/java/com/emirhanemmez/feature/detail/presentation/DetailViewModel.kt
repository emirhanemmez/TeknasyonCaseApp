package com.emirhanemmez.feature.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import com.emirhanemmez.feature.detail.domain.usecase.GetSatelliteDetail
import com.emirhanemmez.feature.detail.domain.usecase.GetSatelliteLastPosition
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@HiltViewModel(assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel @AssistedInject constructor(
    @Assisted private val satelliteId: Int,
    private val getSatelliteDetail: GetSatelliteDetail,
    private val getSatelliteLastPosition: GetSatelliteLastPosition
) : ViewModel() {
    @AssistedFactory
    fun interface Factory {
        fun create(@Assisted satelliteId: Int): DetailViewModel
    }

    var satelliteDetailState by mutableStateOf<SatelliteDetailState>(SatelliteDetailState.Loading)
        private set

    var positionState by mutableStateOf<PositionState>(PositionState.Loading)
        private set

    val timer = Timer()

    init {
        getSatelliteDetail()

        timer.schedule(object : TimerTask() {
            override fun run() {
                getSatelliteLastPosition()
            }

        }, 0, 3000)
    }

    fun getSatelliteDetail() {
        viewModelScope.launch {
            getSatelliteDetail.invoke(satelliteId)
                .onStart {
                    delay(1000)
                }
                .catch {
                    satelliteDetailState =
                        SatelliteDetailState.Error(it.message ?: "An error occurred")
                }
                .collect {
                    satelliteDetailState = SatelliteDetailState.Success(it)
                }
        }
    }

    fun getSatelliteLastPosition() {
        viewModelScope.launch {
            getSatelliteLastPosition.invoke(satelliteId)
                .onStart {
                    positionState = PositionState.Loading
                    delay(1500)
                }.catch {
                    positionState = PositionState.Error(it.message ?: "An error occurred")
                }.collect {
                    positionState = PositionState.Success(it)
                }
        }
    }

    override fun onCleared() {
        timer.cancel()
        super.onCleared()
    }

    sealed class SatelliteDetailState {
        data object Loading : SatelliteDetailState()
        data class Error(val message: String) : SatelliteDetailState()
        data class Success(val satelliteDetail: SatelliteDetail) : SatelliteDetailState()
    }

    sealed class PositionState {
        data object Loading : PositionState()
        data class Error(val message: String) : PositionState()
        data class Success(val lastPosition: Position) : PositionState()
    }
}