package com.safr.binlist.data.local.datasourse

import com.safr.binlist.data.local.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insert(binom: HistoryEntity)

    fun getBinom(): Flow<List<HistoryEntity>>
}