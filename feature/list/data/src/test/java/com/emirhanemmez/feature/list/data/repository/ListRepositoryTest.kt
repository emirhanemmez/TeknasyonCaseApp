package com.emirhanemmez.feature.list.data.repository

import android.content.Context
import android.content.res.Resources
import com.emirhanemmez.feature.list.data.R
import com.emirhanemmez.feature.list.data.model.SatelliteEntity
import com.emirhanemmez.feature.list.data.service.ListService
import com.emirhanemmez.feature.list.domain.model.Satellite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListRepositoryTest : FreeSpec({
    val context = mockk<Context>()
    val resources = mockk<Resources>()
    val listService = spyk(ListService(context))
    val sut = ListRepositoryImpl(listService, Dispatchers.IO)

    val entity = listOf(
        SatelliteEntity(
            id = 1,
            name = "Satellite 1",
            active = true
        )
    )
    every { context.resources } returns resources
    every { resources.openRawResource(R.raw.satellite_list) } returns
            Json.encodeToString(entity)
                .trimIndent()
                .byteInputStream()

    "when getSatellites is called" - {
        val query = ""
        val result = sut.getSatellites(query).first()

        "then it should return a list of satellites" {
            result shouldBe entity.map {
                Satellite(
                    id = it.id,
                    name = it.name,
                    active = it.active
                )
            }
        }
    }
})