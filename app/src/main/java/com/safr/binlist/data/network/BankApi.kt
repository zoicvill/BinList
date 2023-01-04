package com.safr.binlist.data.network

import com.safr.binlist.data.network.model.ResponseDataBank
import retrofit2.http.GET
import retrofit2.http.Path

interface BankApi {

    @GET("{user}")
    suspend fun getBankDetails(
        @Path("user")  type: Int,
    ): ResponseDataBank
}