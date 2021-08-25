package com.example.countries.debendencyinjection

import com.example.countries.repository.CountryRepository
import com.example.countries.view.mainfragment.MainViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject( repository : CountryRepository)

    fun inject( mainViewModel : MainViewModel)
}