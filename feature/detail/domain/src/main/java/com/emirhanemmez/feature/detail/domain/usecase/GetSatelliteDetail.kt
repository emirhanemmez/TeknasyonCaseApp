package com.emirhanemmez.feature.detail.domain.usecase

import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSatelliteDetail @Inject constructor(
    private val detailRepository: DetailRepository
) {
    operator fun invoke(satelliteId: Int): Flow<SatelliteDetail> =
        detailRepository.getSatelliteDetail(satelliteId)
}