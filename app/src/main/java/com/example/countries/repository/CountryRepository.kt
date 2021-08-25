package com.example.countries.repository

import com.example.countries.debendencyinjection.DaggerApiComponent
import com.example.countries.model.CountriesModel
import com.example.countries.retrofit.ConnectionEndPoint
import retrofit2.Response
import javax.inject.Inject

class CountryRepository {

    @Inject
   lateinit var api : ConnectionEndPoint

   init{
       DaggerApiComponent.create().inject(this)
   }

    // fun getCountry repo.
   suspend fun getCountry() : Response<List<CountriesModel>>{
       return api.getCountries()
   }
}