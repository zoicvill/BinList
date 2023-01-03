package com.safr.binlist.di

import com.safr.binlist.data.local.dao.HistoryDao
import com.safr.binlist.data.local.datasourse.LocalDataSource
import com.safr.binlist.data.local.datasourse.LocalDataSourceImpl
import com.safr.binlist.data.network.BankApi
import com.safr.binlist.data.network.datasourse.ApiDataSource
import com.safr.binlist.data.network.datasourse.ApiDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: HistoryDao): LocalDataSource {
        return LocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideApiDataSource(api: BankApi): ApiDataSource {
        return ApiDataSourceImpl(api)
    }
}