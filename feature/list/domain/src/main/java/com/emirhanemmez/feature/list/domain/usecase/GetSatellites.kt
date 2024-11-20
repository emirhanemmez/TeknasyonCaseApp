package com.emirhanemmez.feature.list.domain.usecase

import com.emirhanemmez.feature.list.domain.model.Satellite
import com.emirhanemmez.feature.list.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSatellites @Inject constructor(
    private val listRepository: ListRepository
) {
    operator fun invoke(query: String): Flow<List<Satellite>> {
        return listRepository.getSatellites(query)
    }
}