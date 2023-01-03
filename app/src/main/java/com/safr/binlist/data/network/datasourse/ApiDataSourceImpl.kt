package com.safr.binlist.data.network.datasourse

import com.safr.binlist.data.local.model.Bank
import com.safr.binlist.data.local.model.History
import com.safr.binlist.data.network.BankApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataSourceImpl @Inject constructor(
    private val api: BankApi
) : ApiDataSource {
    override suspend fun getBinlist(type: Int): History? {
        return api.getBankDetails(type)
    }
}