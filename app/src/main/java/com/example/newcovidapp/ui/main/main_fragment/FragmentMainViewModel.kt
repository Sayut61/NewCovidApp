package com.example.newcovidapp.ui.main.main_fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class FragmentMainViewModel: ViewModel() {
    enum class SortType {BY_NAME, BY_CASES}
    private val sortTypeLiveData = MutableLiveData<SortType>(SortType.BY_NAME)

    private val _allCovidInfoByCountryLiveData = MutableLiveData<AllCovidInfoByCountries>()
    val allCovidInfoByCountryLiveData: LiveData<AllCovidInfoByCountries> = _allCovidInfoByCountryLiveData

    private val _countriesInfoLiveData = MutableLiveData<List<DetailCovidInfoByCountry>>()
    val countriesInfoLiveData: LiveData<List<DetailCovidInfoByCountry>> = _countriesInfoLiveData

    private val _exceptionLiveData = MutableLiveData<Exception>()
    val exceptionLiveData: LiveData<Exception> = _exceptionLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    fun changeSortType(type: SortType){
        val unsortedList = _countriesInfoLiveData.value
        if (unsortedList != null){
            val sortedList = when(type){
                SortType.BY_CASES -> unsortedList.sortedBy { it.cases }
                SortType.BY_NAME -> unsortedList.sortedBy { it.country }
            }
            _countriesInfoLiveData.value = sortedList
        }
    }

    fun showAllInfoByCountries(){
        viewModelScope.launch(Dispatchers.Main){
            _progressBarLiveData.value = true
            try {
                val getAllInfoCovid: AllCovidInfoByCountries = service.getAllInfoByAllCountry()
                _allCovidInfoByCountryLiveData.value = getAllInfoCovid
                val getAllNameCountries: List<DetailCovidInfoByCountry> = service.getCountryName()
                _countriesInfoLiveData.value = getAllNameCountries
            }catch (ex: Exception){
                _exceptionLiveData.value = ex
            }
            _progressBarLiveData.value = false
        }
    }
}