package com.emirhanemmez.feature.list.data.repository

import com.emirhanemmez.feature.list.data.mapper.SatelliteMapper
import com.emirhanemmez.feature.list.data.service.ListService
import com.emirhanemmez.feature.list.domain.model.Satellite
import com.emirhanemmez.feature.list.domain.repository.ListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val listService: ListService,
    private val ioDispatcher: CoroutineDispatcher
) : ListRepository {
    override fun getSatellites(query: String): Flow<List<Satellite>> =
        flow {
            emit(listService.getSatellites(query))
        }.map { satelliteList ->
            satelliteList.map {
                SatelliteMapper.mapToDomain(it)
            }
        }.flowOn(ioDispatcher)
}