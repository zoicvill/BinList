package com.safr.binlist.data.local.datasourse

import com.safr.binlist.data.local.dao.HistoryDao
import com.safr.binlist.data.local.model.Bank
import com.safr.binlist.data.local.model.History
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: HistoryDao
) : LocalDataSource {
    override suspend fun insert(binom: History): Long? {
      return  binom.let { dao.insert(it) }
    }

    override suspend fun getBinom(): List<History> {
        return dao.getFavourites()
    }

}