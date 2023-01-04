package com.safr.binlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safr.binlist.data.local.dao.HistoryDao
import com.safr.binlist.data.local.model.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase(){

    abstract fun getHistory(): HistoryDao

    companion object {
        const val DATABASE_NAME: String = "banks_db"
    }

}