package com.example.countries.view.mainfragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.model.CountriesModel
import com.example.countries.repository.CountryRepository
import com.example.countries.retrofit.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var countries           = MutableLiveData<List<CountriesModel>>()
    var countryLoadError    = MutableLiveData<Boolean>()
    var loading             = MutableLiveData<Boolean>()

    private val countryRepository = CountryRepository()


    // fun refresh.
    fun refresh(){
        // fun fetch data.
        fetchCountries()
    }

    // fun fetch data from api.
    private fun fetchCountries(){
        // show progress bar.
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch{
            var response = countryRepository.getCountry()
            CoroutineScope(Dispatchers.Main).async {
                if(response.isSuccessful){
                    countries.value = response.body()!!

                    // hide text error.
                    countryLoadError.value = false
                    // hide progress bar.
                    loading.value = false
                }else{
                    // show text error.
                    countryLoadError.value = true
                    // hide progress bar.
                    loading.value = false
                }
            }
        }
    }
}