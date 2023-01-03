package com.safr.binlist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Bank(@SerializedName("nameBank"  ) var name  : String? = null,
                @SerializedName("url"   ) var url   : String? = null,
                @SerializedName("phone" ) var phone : String? = null,
                @SerializedName("city"  ) var city  : String? = null)
