package com.example.newcovidapp.ui.main.mainAll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.newcovidapp.R
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import com.example.newcovidapp.ui.main.mainDetail.CountryDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), CovidAdapterListener, MainViewInterface {
    val presenter = MainActivityPresenter(this)

    override fun onCountryClick(country: DetailCovidInfoByCountry) {
        val intent = Intent(this@MainActivity, CountryDetailActivity::class.java)
        intent.putExtra("nameCountry", country.country)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.showAllInfoByCountries()
    }

   override fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
   override fun showAllCovidInfoByAllCountries(getAllInfoCovid: AllCovidInfoByCountries){
        allCasesTextView.text = "Случаев всего: ${getAllInfoCovid.cases}"
        allTodayCasesTextView.text = "Случаев сегодня: ${getAllInfoCovid.todayCases}"
        allDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.deaths}"
        allTodayDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.todayDeaths}"
        allRecoveredTextView.text = "Выздоравлений всего: ${getAllInfoCovid.recovered}"
        allTodayRecoveredTextView.text = "Выздоравлений сегодня: ${getAllInfoCovid.todayRecovered}"
    }
   override fun showAllCountriesName(getAllNameCountries: List<DetailCovidInfoByCountry>){
        val adapter = CovidAdapter(getAllNameCountries, this@MainActivity)
        recyclerViewCountries.adapter = adapter
    }
   override fun showError(ex: Exception){
        Toast.makeText(this@MainActivity, "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
    }
   override fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }
}