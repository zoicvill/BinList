package com.safr.binlist.di

import android.content.Context
import androidx.room.Room
import com.safr.binlist.data.local.HistoryDatabase
import com.safr.binlist.data.local.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDrinkDb(@ApplicationContext context: Context) : HistoryDatabase {
        return Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            HistoryDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDrinkDAO(cocktailDatabase: HistoryDatabase) : HistoryDao {
        return cocktailDatabase.getHistory()
    }

}