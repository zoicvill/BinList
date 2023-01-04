package com.safr.binlist.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseDataBank(
    @field: SerializedName("type") val type: String? = null,
    @field:SerializedName("scheme") val scheme: String? = null,
    @field:SerializedName("brand") val brand: String? = null,

    @field:SerializedName("country") val country: Country? = Country(),
    @field:SerializedName("bank") val bank: Bank? = Bank(),
    @field:SerializedName("prepaid") val prepaid: String? = null,
    @field:SerializedName("number") val number: Number? = Number()
)

data class Country(
    @field:SerializedName("numeric") val numeric: String? = null,
    @field:SerializedName("alpha2") val alpha2: String? = null,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("emoji") val emoji: String? = null,
    @field:SerializedName("currency") val currency: String? = null,
    @field:SerializedName("latitude") val latitude: String? = null,
    @field:SerializedName("longitude") val longitude: String? = null
)

data class Bank(
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("url") val url: String? = null,
    @field:SerializedName("phone") val phone: String? = null,
    @field:SerializedName("city") val city: String? = null
)

data class Number(
    @field:SerializedName("length") val length: String? = null,
    @field:SerializedName("luhn") val luhn: String? = null
)




