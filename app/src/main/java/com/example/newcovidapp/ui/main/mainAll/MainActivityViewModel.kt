package com.example.newcovidapp.ui.main.mainAll
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivityViewModel: ViewModel() {
    private val _allCovidInfoByCountryLiveData = MutableLiveData<AllCovidInfoByCountries>()
    val allCovidInfoByCountryLiveData: LiveData<AllCovidInfoByCountries> = _allCovidInfoByCountryLiveData
    val countryNameLiveData = MutableLiveData<List<DetailCovidInfoByCountry>>()
    val exceptionLiveData = MutableLiveData<Exception>()
    val progresBarLiveData = MutableLiveData<Boolean>()
    fun showAllInfoByCountries(){
        GlobalScope.launch(Dispatchers.Main){
            progresBarLiveData.value = true
            try {
                val getAllInfoCovid: AllCovidInfoByCountries = service.getAllInfoByAllCountry()
                _allCovidInfoByCountryLiveData.value = getAllInfoCovid
                val getAllNameCountries: List<DetailCovidInfoByCountry> = service.getCountryName()
                countryNameLiveData.value = getAllNameCountries
            }catch (ex: Exception){
                exceptionLiveData.value = ex
            }
            progresBarLiveData.value = false
        }
    }
}