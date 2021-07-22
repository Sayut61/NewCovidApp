package com.example.newcovidapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.coroutines.launch

class CountryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val countryName = intent.getStringExtra("nameCountry")
        lifecycleScope.launch {
            val countries = service.getCountryName()
            val country = countries.find { it.country == countryName}!!
            
            detailNameCountryTextView.text = countryName
            detailCasesTextView.text = "Случаев всeего: ${country.cases}"
            detailTodayCasesTextView.text = "Случаев сегодня: ${country.todayCases}"
            detailDeathsTextView.text = "Смертей всего: ${country.deaths}"
            detailTodayDeathsTextView.text = "Смертей сегодня: ${country.todayDeaths}"
            detailRecoveredTextView.text = "Выздоровлений всего: ${country.recovered}"
            detailTodayRecoveredTextView.text = "Выздоровлений сегодня: ${country.todayRecovered}"
        }
    }
}