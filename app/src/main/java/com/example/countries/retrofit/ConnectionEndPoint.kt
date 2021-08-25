package com.example.countries.retrofit

import com.example.countries.model.CountriesModel
import com.example.countries.util.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ConnectionEndPoint {

    @GET(END_POINT)
    suspend fun getCountries() : Response<List<CountriesModel>>
}