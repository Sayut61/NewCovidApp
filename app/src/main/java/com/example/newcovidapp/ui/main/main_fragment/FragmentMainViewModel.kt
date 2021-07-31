package com.example.newcovidapp.ui.main.main_fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


open class FragmentMainViewModel: ViewModel() {
    private val _allCovidInfoLiveData = MutableLiveData<AllCovidInfo>()
    val allCovidInfoLiveData: LiveData<AllCovidInfo> = _allCovidInfoLiveData

    private val _countriesInfoFromApiLiveData = MutableLiveData<List<CountryCovidInfo>>()
    val countryNameLiveData = MediatorLiveData<List<CountryCovidInfo>>()

    private val _exceptionLiveData = MutableLiveData<Exception>()
    val exceptionLiveData: LiveData<Exception> = _exceptionLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    private val filterTextLiveData = MutableLiveData<String>()

    enum class SortType{BY_NAME, BY_CASES}
    private val sortTypeLiveData = MutableLiveData<SortType>(SortType.BY_NAME)

    init {
        countryNameLiveData.addSource(_countriesInfoFromApiLiveData){
            countryNameLiveData.value = prepareCountryInfoList()
        }
        countryNameLiveData.addSource(filterTextLiveData){
            countryNameLiveData.value = prepareCountryInfoList()
        }
        countryNameLiveData.addSource(sortTypeLiveData){
            countryNameLiveData.value = prepareCountryInfoList()
        }
    }

    private fun prepareCountryInfoList(): List<CountryCovidInfo>{
        val  unfiltered = _countriesInfoFromApiLiveData.value
        val filtered = unfiltered?.filter {
            it.country.startsWith(filterTextLiveData.value?:"", true)
        } ?: emptyList()
        return when(sortTypeLiveData.value!!){
            SortType.BY_NAME -> filtered.sortedBy { it.country }
            SortType.BY_CASES -> filtered.sortedBy { it.cases }
        }
    }

    fun changeFilterText(filterText: String?){
        filterTextLiveData.value = filterText
    }

    fun changeSortType(type: SortType){
        sortTypeLiveData.value = type
    }

    fun refreshAllAndCountriesInfo(){
        GlobalScope.launch(Dispatchers.Main){
            _progressBarLiveData.value = true
            try {
                val getAllInfoCovid: AllCovidInfo = service.getAllInfoByAllCountry()
                _allCovidInfoLiveData.value = getAllInfoCovid
                val getAllNameCountryCovidInfos: List<CountryCovidInfo> = service.getCountryName()
                _countriesInfoFromApiLiveData.value = getAllNameCountryCovidInfos
            }catch (ex: Exception){
                _exceptionLiveData.value = ex
            }
            _progressBarLiveData.value = false
        }
    }

}