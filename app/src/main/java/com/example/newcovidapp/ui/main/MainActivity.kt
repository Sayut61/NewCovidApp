package com.example.newcovidapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.newcovidapp.R
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(), CovidAdapterListener {
    override fun onCountryClick(country: DetailCovidInfoByCountry) {
        val intent = Intent(this@MainActivity, CountryDetailActivity::class.java)
        intent.putExtra("nameCountry", country.country)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch{
            progressBar.visibility = View.VISIBLE
            try {
                val getAllInfoCovid: AllCovidInfoByCountries = service.getAllInfoByAllCountry()
                allCasesTextView.text = "Случаев всего: ${getAllInfoCovid.cases}"
                allTodayCasesTextView.text = "Случаев сегодня: ${getAllInfoCovid.todayCases}"
                allDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.deaths}"
                allTodayDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.todayDeaths}"
                allRecoveredTextView.text = "Выздоравлений всего: ${getAllInfoCovid.recovered}"
                allTodayRecoveredTextView.text = "Выздоравлений сегодня: ${getAllInfoCovid.todayRecovered}"

                val getAllNameCountries: List<DetailCovidInfoByCountry> = service.getCountryName()
                val adapter = CovidAdapter(getAllNameCountries, this@MainActivity)
                recyclerViewCountries.adapter = adapter

            }catch (ex: Exception){
                Toast.makeText(this@MainActivity, "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
            }
            progressBar.visibility = View.INVISIBLE
        }
    }
}