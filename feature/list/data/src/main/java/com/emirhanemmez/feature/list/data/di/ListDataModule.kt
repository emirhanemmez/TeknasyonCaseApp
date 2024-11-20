package com.emirhanemmez.feature.list.data.di

import android.content.Context
import com.emirhanemmez.feature.list.data.repository.ListRepositoryImpl
import com.emirhanemmez.feature.list.data.service.ListService
import com.emirhanemmez.feature.list.domain.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ListDataModule {
    @Provides
    @Singleton
    fun provideListService(
        @ApplicationContext context: Context
    ): ListService {
        return ListService(context)
    }

    @Provides
    @Singleton
    fun provideListRepository(
        listService: ListService,
        dispatcher: CoroutineDispatcher
    ): ListRepository {
        return ListRepositoryImpl(listService, dispatcher)
    }
}