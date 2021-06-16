package com.example.countries.repository

import com.example.countries.model.CountriesModel
import com.example.countries.retrofit.ConnectionEndPoint
import com.example.countries.util.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRepository {

    var api : ConnectionEndPoint = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ConnectionEndPoint::class.java)

    // fun getCountry repo.
   suspend fun getCountry() : Response<List<CountriesModel>>{
       return api.getCountries()
   }


}