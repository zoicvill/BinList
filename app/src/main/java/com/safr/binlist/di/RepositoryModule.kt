package com.safr.binlist.di

import com.safr.binlist.data.local.datasourse.LocalDataSource
import com.safr.binlist.data.network.datasourse.ApiDataSource
import com.safr.binlist.data.repository.RepositoryImpl
import com.safr.binlist.domain.repository.BankRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCocktailRepository(
        lanApi: ApiDataSource,
        roomDao: LocalDataSource
    ): BankRepository {
        return RepositoryImpl(api = lanApi, dao = roomDao)
    }
}