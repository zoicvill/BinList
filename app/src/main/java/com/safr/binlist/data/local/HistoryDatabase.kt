package com.safr.binlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safr.binlist.data.local.dao.HistoryDao
import com.safr.binlist.data.local.model.History

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase(){

    abstract fun getHistory(): HistoryDao

    companion object {
        const val DATABASE_NAME: String = "drinks_db"
    }

}