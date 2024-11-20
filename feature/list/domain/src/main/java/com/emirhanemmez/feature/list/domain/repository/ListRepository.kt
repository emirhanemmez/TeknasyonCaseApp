package com.emirhanemmez.feature.list.domain.repository

import com.emirhanemmez.feature.list.domain.model.Satellite
import kotlinx.coroutines.flow.Flow

fun interface ListRepository {
    fun getSatellites(query: String): Flow<List<Satellite>>
}