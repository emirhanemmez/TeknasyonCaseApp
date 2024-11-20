package com.emirhanemmez.feature.detail.data.di

import android.content.Context
import androidx.room.Room
import com.emirhanemmez.feature.detail.data.dao.SatelliteDetailDao
import com.emirhanemmez.feature.detail.data.database.SatelliteDetailDatabase
import com.emirhanemmez.feature.detail.data.repository.DetailRepositoryImpl
import com.emirhanemmez.feature.detail.data.service.DetailService
import com.emirhanemmez.feature.detail.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDataModule {
    @Provides
    @Singleton
    fun provideSatelliteDetailDatabase(
        @ApplicationContext context: Context
    ): SatelliteDetailDatabase =
        Room.databaseBuilder(
            context,
            SatelliteDetailDatabase::class.java,
            "satellite_detail_database"
        ).build()

    @Provides
    @Singleton
    fun provideSatelliteDetailDao(
        database: SatelliteDetailDatabase
    ): SatelliteDetailDao = database.satelliteDetailDao()

    @Provides
    @Singleton
    fun provideDetailService(
        @ApplicationContext context: Context
    ): DetailService {
        return DetailService(context)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        detailService: DetailService,
        satelliteDetailDao: SatelliteDetailDao,
        dispatcher: CoroutineDispatcher
    ): DetailRepository {
        return DetailRepositoryImpl(detailService, satelliteDetailDao, dispatcher)
    }
}