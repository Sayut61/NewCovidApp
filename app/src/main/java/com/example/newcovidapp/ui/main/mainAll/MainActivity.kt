package com.example.newcovidapp.ui.main.mainAll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.newcovidapp.R
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import com.example.newcovidapp.ui.main.mainDetail.CountryDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), CovidAdapterListener {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCountryClick(country: DetailCovidInfoByCountry) {
        val intent = Intent(this@MainActivity, CountryDetailActivity::class.java)
        intent.putExtra("nameCountry", country.country)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.showAllInfoByCountries()
        viewModel.allCovidInfoByCountryLiveData.observe(this){allCovidInfo->
            showAllCovidInfoByAllCountries(allCovidInfo)
        }
        viewModel.countryNameLiveData.observe(this){allNameCounties->
            showAllCountriesName(allNameCounties)
        }
        viewModel.exceptionLiveData.observe(this){exception->
            showError(exception)
        }
        viewModel.progresBarLiveData.observe(this){progressBar->
            if(progressBar == true)
                showProgressBar()
            else
                hideProgressBar()
        }
    }

   fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
   fun showAllCovidInfoByAllCountries(getAllInfoCovid: AllCovidInfoByCountries){
        allCasesTextView.text = "Случаев всего: ${getAllInfoCovid.cases}"
        allTodayCasesTextView.text = "Случаев сегодня: ${getAllInfoCovid.todayCases}"
        allDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.deaths}"
        allTodayDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.todayDeaths}"
        allRecoveredTextView.text = "Выздоравлений всего: ${getAllInfoCovid.recovered}"
        allTodayRecoveredTextView.text = "Выздоравлений сегодня: ${getAllInfoCovid.todayRecovered}"
    }
   fun showAllCountriesName(getAllNameCountries: List<DetailCovidInfoByCountry>){
        val adapter = CovidAdapter(getAllNameCountries, this@MainActivity)
        recyclerViewCountries.adapter = adapter
    }
   fun showError(ex: Exception){
        Toast.makeText(this@MainActivity, "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
    }
   fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }
}