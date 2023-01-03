package com.safr.binlist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Country(@SerializedName("numeric"   ) var numeric   : String? = null,
                   @SerializedName("alpha2"    ) var alpha2    : String? = null,
                   @SerializedName("nameCountry") var name_      : String? = null,
                   @SerializedName("emoji"     ) var emoji     : String? = null,
                   @SerializedName("currency"  ) var currency  : String? = null,
                   @SerializedName("latitude"  ) var latitude  : Int?    = null,
                   @SerializedName("longitude" ) var longitude : Int?    = null)
