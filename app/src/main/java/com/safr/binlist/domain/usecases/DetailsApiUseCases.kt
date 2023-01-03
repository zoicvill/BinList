package com.safr.binlist.domain.usecases

import android.util.Log
import com.safr.binlist.core.DataState
import com.safr.binlist.data.local.model.History
import com.safr.binlist.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsApiUseCases @Inject constructor(private val rep: BankRepository) {
    suspend fun getDetails(id: Int): Flow<DataState<History?>> {
        Log.d("lol", "CocktailDetailsApiUseCases $id")
        return rep.getHistoryBank(id)
    }
}