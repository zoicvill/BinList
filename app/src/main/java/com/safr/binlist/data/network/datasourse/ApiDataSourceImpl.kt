package com.safr.binlist.data.network.datasourse

import com.safr.binlist.data.network.BankApi
import com.safr.binlist.data.network.model.ResponseDataBank
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataSourceImpl @Inject constructor(
    private val api: BankApi
) : ApiDataSource {
    override suspend fun getBinlist(type: Int): ResponseDataBank {
        return api.getBankDetails(type)
    }
}