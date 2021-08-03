package com.example.newcovidapp.ui.main.detail_fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcovidapp.data.CountryCovidInfo
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    private val _detailCovidInfoByCountryLiveData = MutableLiveData<CountryCovidInfo>()
    val detailCountryCovidInfoLiveData: LiveData<CountryCovidInfo> = _detailCovidInfoByCountryLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    private val _showExceptionLiveData = MutableLiveData<Exception>()
    val showExceptionLiveData: LiveData<Exception> = _showExceptionLiveData

    fun showDetailInfoByCountry(countryName: String){
        GlobalScope.launch(Dispatchers.Main) {
            _progressBarLiveData.value = true
            try {
                val countries = service.getCountryName()
                val country = countries.find { it.country == countryName }!!
                _detailCovidInfoByCountryLiveData.value = country
            }catch (ex: Exception){
                _showExceptionLiveData.value = ex
            }
            _progressBarLiveData.value = false
        }
    }
}