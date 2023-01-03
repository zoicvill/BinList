package com.safr.binlist.data.network

import com.safr.binlist.data.local.model.Bank
import com.safr.binlist.data.local.model.History
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BankApi {

    @GET("{user}")
    suspend fun getBankDetails(
        @Path("user")  type: Int,
    ): History
}