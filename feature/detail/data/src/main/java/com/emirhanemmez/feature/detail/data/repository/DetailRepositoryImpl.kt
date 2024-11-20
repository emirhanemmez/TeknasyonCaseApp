package com.emirhanemmez.feature.detail.data.repository

import com.emirhanemmez.feature.detail.data.dao.SatelliteDetailDao
import com.emirhanemmez.feature.detail.data.mapper.PositionMapper
import com.emirhanemmez.feature.detail.data.mapper.SatelliteDetailMapper
import com.emirhanemmez.feature.detail.data.service.DetailService
import com.emirhanemmez.feature.detail.domain.model.Position
import com.emirhanemmez.feature.detail.domain.model.SatelliteDetail
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailService: DetailService,
    private val satelliteDetailDao: SatelliteDetailDao,
    private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun getSatelliteDetail(satelliteId: Int): Flow<SatelliteDetail> =
        flow {
            val dbEntity = satelliteDetailDao.getSatelliteDetail(satelliteId).firstOrNull()
            if (dbEntity != null) {
                emit(SatelliteDetailMapper.dbEntityToDomain(dbEntity))
            } else {
                val satelliteDetailEntity = detailService.getSatelliteDetail(satelliteId)
                satelliteDetailDao.insertSatelliteDetail(
                    SatelliteDetailMapper.entityToDbEntity(satelliteDetailEntity)
                )
                emit(SatelliteDetailMapper.entityToDomain(satelliteDetailEntity))
            }
        }.flowOn(ioDispatcher)

    override fun getSatelliteLastPosition(satelliteId: Int): Flow<Position> =
        flow {
            emit(detailService.getSatelliteLastPosition(satelliteId))
        }.map {
            PositionMapper.mapToDomain(it)
        }.flowOn(ioDispatcher)
}