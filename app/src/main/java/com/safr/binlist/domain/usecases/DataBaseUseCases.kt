package com.safr.binlist.domain.usecases

import com.safr.binlist.data.local.model.HistoryEntity
import com.safr.binlist.domain.repository.BankRepository
import javax.inject.Inject

class DataBaseUseCases @Inject constructor(private val rep: BankRepository) {
     fun getDbHistory() = rep.getToDB()

    suspend fun insDbHistory(histr: HistoryEntity){
        rep.addToDB(histr)
    }
}