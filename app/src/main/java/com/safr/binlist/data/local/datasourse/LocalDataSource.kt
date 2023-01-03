package com.safr.binlist.data.local.datasourse

import com.safr.binlist.data.local.model.Bank
import com.safr.binlist.data.local.model.History

interface LocalDataSource {
    suspend fun insert(cocktail: History): Long?

    suspend fun getBinom(): List<History>
}