package com.example.newcovidapp.ui.main.mainDetail
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CountryDetailViewModel: ViewModel() {
    val detailCovidInfoByCountryLiveData = MutableLiveData<DetailCovidInfoByCountry>()
    fun onViewCreated(countryName: String){
        GlobalScope.launch(Dispatchers.Main) {
            val countries = service.getCountryName()
            val country = countries.find { it.country == countryName}!!
            detailCovidInfoByCountryLiveData.value = country
        }
    }
}