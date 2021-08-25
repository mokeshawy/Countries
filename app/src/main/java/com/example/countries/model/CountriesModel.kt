package com.example.countries.model

import com.google.gson.annotations.SerializedName

data class CountriesModel(

    @SerializedName("name")
    val countryName : String,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("flagPNG")
    val flag: String?
)