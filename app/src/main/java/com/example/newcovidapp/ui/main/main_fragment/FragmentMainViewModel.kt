package com.example.newcovidapp.ui.main.main_fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class FragmentMainViewModel: ViewModel() {
    private val _allCovidInfoByCountryLiveData = MutableLiveData<AllCovidInfoByCountries>()
    val allCovidInfoByCountryLiveData: LiveData<AllCovidInfoByCountries> = _allCovidInfoByCountryLiveData

    private val _countryNameLiveData = MutableLiveData<List<DetailCovidInfoByCountry>>()
    val countryNameLiveData: LiveData<List<DetailCovidInfoByCountry>> = _countryNameLiveData

    private val _exceptionLiveData = MutableLiveData<Exception>()
    val exceptionLiveData: LiveData<Exception> = _exceptionLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    fun showAllInfoByCountries(){
        viewModelScope.launch(Dispatchers.Main){
            _progressBarLiveData.value = true
            try {
                val getAllInfoCovid: AllCovidInfoByCountries = service.getAllInfoByAllCountry()
                _allCovidInfoByCountryLiveData.value = getAllInfoCovid
                val getAllNameCountries: List<DetailCovidInfoByCountry> = service.getCountryName()
                _countryNameLiveData.value = getAllNameCountries
            }catch (ex: Exception){
                _exceptionLiveData.value = ex
            }
            _progressBarLiveData.value = false
        }
    }
}