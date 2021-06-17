package com.example.countries.view.mainfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.debendencyinjection.DaggerApiComponent
import com.example.countries.model.CountriesModel
import com.example.countries.repository.CountryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    var countries           = MutableLiveData<List<CountriesModel>>()
    var countryLoadError    = MutableLiveData<Boolean>()
    var loading             = MutableLiveData<Boolean>()


    @Inject
    lateinit var countryRepository : CountryRepository
    init {
        DaggerApiComponent.create().inject(this)
    }

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
            CoroutineScope(Dispatchers.Main).launch {
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