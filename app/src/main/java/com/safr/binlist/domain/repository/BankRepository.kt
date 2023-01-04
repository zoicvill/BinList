package com.safr.binlist.domain.repository

import com.safr.binlist.core.DataState
import com.safr.binlist.data.local.model.HistoryEntity
import com.safr.binlist.data.network.model.ResponseDataBank
import kotlinx.coroutines.flow.Flow

interface BankRepository {

    suspend fun getHistoryBank(binId: Int): Flow<DataState<ResponseDataBank?>>

    suspend fun addToDB(hist: HistoryEntity)

    fun getToDB(): Flow<List<HistoryEntity>>
}