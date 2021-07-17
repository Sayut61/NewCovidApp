package com.example.newcovidapp.ui.main.mainAll
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

interface MainViewInterface{
    fun showProgressBar()
    fun showAllCovidInfoByAllCountries(getAllInfoCovid: AllCovidInfoByCountries)
    fun showAllCountriesName(getAllNameCountries: List<DetailCovidInfoByCountry>)
    fun showError(ex: Exception)
    fun hideProgressBar()
}

class MainActivityPresenter(val view: MainViewInterface) {

    fun showAllInfoByCountries(){
        GlobalScope.launch(Dispatchers.Main){
            view.showProgressBar()
            try {
                val getAllInfoCovid: AllCovidInfoByCountries = service.getAllInfoByAllCountry()
                view.showAllCovidInfoByAllCountries(getAllInfoCovid)

                val getAllNameCountries: List<DetailCovidInfoByCountry> = service.getCountryName()
                view.showAllCountriesName(getAllNameCountries)

            }catch (ex: Exception){
                view.showError(ex)
            }
            view.hideProgressBar()
        }
    }
}