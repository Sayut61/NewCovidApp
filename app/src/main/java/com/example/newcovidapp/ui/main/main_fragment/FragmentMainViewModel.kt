package com.example.newcovidapp.ui.main.main_fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo
import com.example.newcovidapp.datasource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


open class FragmentMainViewModel: ViewModel() {
    private val _allCovidInfoByCountryLiveData = MutableLiveData<AllCovidInfo>()
    val allCovidInfoByCountryLiveData: LiveData<AllCovidInfo> = _allCovidInfoByCountryLiveData

    private val _countryNameLiveData = MutableLiveData<List<CountryCovidInfo>>()
    val countryNameLiveData: LiveData<List<CountryCovidInfo>> = _countryNameLiveData

    private val _exceptionLiveData = MutableLiveData<Exception>()
    val exceptionLiveData: LiveData<Exception> = _exceptionLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    fun showAllInfoByCountries(){
        GlobalScope.launch(Dispatchers.Main){
            _progressBarLiveData.value = true
            try {
                val getAllInfoCovid = RemoteDataSource.getAllCovidInfo()
                _allCovidInfoByCountryLiveData.value = getAllInfoCovid
                val getAllNameCountries = RemoteDataSource.getCountriesInfo()
                _countryNameLiveData.value = getAllNameCountries
            }catch (ex: Exception){
                _exceptionLiveData.value = ex
            }
            _progressBarLiveData.value = false
        }
    }

}