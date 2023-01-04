package com.safr.binlist.data.local.dao

import androidx.room.*
import com.safr.binlist.data.local.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(card : HistoryEntity)

    @Query("SELECT * FROM historySend")
    fun getAllHistory() : Flow<List<HistoryEntity>>
}