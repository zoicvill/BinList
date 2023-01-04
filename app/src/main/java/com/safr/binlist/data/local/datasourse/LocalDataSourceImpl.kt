package com.safr.binlist.data.local.datasourse

import com.safr.binlist.data.local.dao.HistoryDao
import com.safr.binlist.data.local.model.HistoryEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: HistoryDao
) : LocalDataSource {
    override suspend fun insert(binom: HistoryEntity) {
      return  binom.let { dao.insertHistory(it) }
    }

    override fun getBinom() = dao.getAllHistory()


}