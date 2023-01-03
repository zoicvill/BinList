package com.safr.binlist.data.repository

import com.safr.binlist.core.DataState
import com.safr.binlist.data.local.datasourse.LocalDataSource
import com.safr.binlist.data.local.model.History
import com.safr.binlist.data.network.datasourse.ApiDataSource
import com.safr.binlist.domain.repository.BankRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val dao: LocalDataSource,
    private val api: ApiDataSource
): BankRepository{
    override suspend fun getHistoryBank(binId: Int): Flow<DataState<History?>> = flow {
        emit(DataState.Loading)
        emit(DataState.Success(api.getBinlist(binId)))
    }.catch { emit(DataState.Error(it)) }
        .flowOn(Dispatchers.IO)


    override suspend fun addToDB(hist: History) {
       dao.insert(hist)
    }
}