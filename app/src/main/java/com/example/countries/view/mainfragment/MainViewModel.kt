package com.example.countries.view.mainfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.CountriesModel

class MainViewModel : ViewModel() {

    var countries           = MutableLiveData<List<CountriesModel>>()
    var countryLoadError    = MutableLiveData<Boolean>()
    var loading             = MutableLiveData<Boolean>()


    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries(){
        val moCkData = listOf(
            CountriesModel("Country A"),
            CountriesModel("Country B"),
            CountriesModel("Country C"),
            CountriesModel("Country D"),
            CountriesModel("Country E"),
            CountriesModel("Country F"),
            CountriesModel("Country G"),
            CountriesModel("Country H"),
            CountriesModel("Country I"),
            CountriesModel("Country J")
        )

        countryLoadError.value  = false
        loading.value           = false
        countries.value         = moCkData
    }
}