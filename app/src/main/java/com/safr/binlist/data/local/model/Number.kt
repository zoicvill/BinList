package com.safr.binlist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Number(

    @SerializedName("length")var length: Int? = null,
    @SerializedName("luhn") var luhn: Boolean? = null

)