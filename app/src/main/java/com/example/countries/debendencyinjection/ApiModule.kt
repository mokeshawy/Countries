package com.example.countries.debendencyinjection

import com.example.countries.repository.CountryRepository
import com.example.countries.retrofit.ConnectionEndPoint
import com.example.countries.util.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideCountriesApi() : ConnectionEndPoint{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConnectionEndPoint::class.java)
    }

    @Provides
    fun providerCountriesService() : CountryRepository{
        return CountryRepository()
    }
}