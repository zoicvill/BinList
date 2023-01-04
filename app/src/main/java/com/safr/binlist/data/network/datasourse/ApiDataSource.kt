package com.safr.binlist.data.network.datasourse

import com.safr.binlist.data.network.model.ResponseDataBank

interface ApiDataSource {
    suspend fun getBinlist(type: Int): ResponseDataBank?
}