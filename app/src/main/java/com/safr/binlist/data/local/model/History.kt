package com.safr.binlist.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "historyLocalMods")
data class History
    (
    @PrimaryKey(autoGenerate = true)
    var idHistory: Int? = 0,
    @SerializedName("number") @Embedded var number: Number? = Number(),
    @SerializedName("scheme") var scheme: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("prepaid") var prepaid: Boolean? = null,
    @SerializedName("country") @Embedded var country: Country? = Country(),
    @SerializedName("bank") @Embedded var bank: Bank? = Bank()
)