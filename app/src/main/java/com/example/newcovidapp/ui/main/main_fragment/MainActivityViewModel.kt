package com.example.newcovidapp.ui.main.main_fragment
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

    private val _countryNameLiveData = MutableLiveData<List<DetailCovidInfoByCountry>>()
    val countryNameLiveData: LiveData<List<DetailCovidInfoByCountry>> = _countryNameLiveData

    private val _exceptionLiveData = MutableLiveData<Exception>()
    val exceptionLiveData: LiveData<Exception> = _exceptionLiveData

    private val _progresBarLiveData = MutableLiveData<Boolean>()
    val progresBarLiveData: LiveData<Boolean> = _progresBarLiveData

    fun showAllInfoByCountries(){
        GlobalScope.launch(Dispatchers.Main){
            _progresBarLiveData.value = true
            try {
                val getAllInfoCovid: AllCovidInfoByCountries = service.getAllInfoByAllCountry()
                _allCovidInfoByCountryLiveData.value = getAllInfoCovid
                val getAllNameCountries: List<DetailCovidInfoByCountry> = service.getCountryName()
                _countryNameLiveData.value = getAllNameCountries
            }catch (ex: Exception){
                _exceptionLiveData.value = ex
            }
            _progresBarLiveData.value = false
        }
    }
}