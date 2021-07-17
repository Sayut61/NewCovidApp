package com.example.newcovidapp.ui.main.mainDetail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CountryDetailViewModel: ViewModel() {
    private val _detailCovidInfoByCountryLiveData = MutableLiveData<DetailCovidInfoByCountry>()
    val detailCovidInfoByCountryLiveData: LiveData<DetailCovidInfoByCountry> = _detailCovidInfoByCountryLiveData

    fun showDetailInfoByCountry(countryName: String){
        GlobalScope.launch(Dispatchers.Main) {
            val countries = service.getCountryName()
            val country = countries.find { it.country == countryName}!!
            _detailCovidInfoByCountryLiveData.value = country
        }
    }
}