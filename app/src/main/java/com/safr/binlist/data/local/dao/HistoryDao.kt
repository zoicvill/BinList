package com.safr.binlist.data.local.dao

import androidx.room.*
import com.safr.binlist.data.local.model.Bank
import com.safr.binlist.data.local.model.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(his: History) : Long

    @Query("SELECT * FROM historyLocalMods")
    suspend fun getFavourites() : List<History>

    @Delete
    suspend fun remove(cocktail: History)

//    @Query("SELECT EXISTS(SELECT * FROM historyLocalMods WHERE bank = :id)")
//    suspend fun isFavourite(id : Bank) : Boolean
}