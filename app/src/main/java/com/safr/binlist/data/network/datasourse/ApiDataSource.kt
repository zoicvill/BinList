package com.safr.binlist.data.network.datasourse

import com.safr.binlist.data.local.model.Bank
import com.safr.binlist.data.local.model.History

interface ApiDataSource {
    suspend fun getBinlist(type: Int): History?
}