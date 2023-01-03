package com.safr.binlist.domain.repository

import com.safr.binlist.core.DataState
import com.safr.binlist.data.local.model.History
import kotlinx.coroutines.flow.Flow

interface BankRepository {

    suspend fun getHistoryBank(binId: Int): Flow<DataState<History?>>

    suspend fun addToDB(hist: History)
}