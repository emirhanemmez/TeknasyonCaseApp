package com.emirhanemmez.feature.detail.domain.usecase

import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSatelliteLastPosition @Inject constructor(
    private val detailRepository: DetailRepository
) {
    operator fun invoke(satelliteId: Int): Flow<Position> =
        detailRepository.getSatelliteLastPosition(satelliteId)
}