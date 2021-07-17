package com.example.newcovidapp.ui.main.mainDetail
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface CountryDetailViewInterface{
    fun showDetailInfoByCountry(country: DetailCovidInfoByCountry)
}

class CountryDetailPresenter(val view: CountryDetailViewInterface){
    fun onViewCreated(countryName: String){
        GlobalScope.launch(Dispatchers.Main) {
            val countries = service.getCountryName()
            val country = countries.find { it.country == countryName}!!
            view.showDetailInfoByCountry(country)
        }
    }
}