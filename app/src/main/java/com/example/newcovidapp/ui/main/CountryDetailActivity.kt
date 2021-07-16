package com.example.newcovidapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.activity_country_detail.*

class CountryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val countryName = intent.getStringExtra("name")
        val cases = intent.getLongExtra("cases", 0)
        val todayCases = intent.getLongExtra("todayCases", 0)
        val deaths = intent.getLongExtra("deaths", 0)
        val todayDeaths = intent.getLongExtra("todayDeaths", 0)
        val recovered = intent.getLongExtra("recovered", 0)
        val todayRecovered = intent.getLongExtra("todayRecovered", 0)

        detailNameCountryTextView.text = countryName
        detailCasesTextView.text = "Случаев всего: ${cases}"
        detailTodayCasesTextView.text = "Случаев сегодня: ${todayCases}"
        detailDeathsTextView.text = "Смертей всего: ${deaths}"
        detailTodayDeathsTextView.text = "Смертей сегодня: ${todayDeaths}"
        detailRecoveredTextView.text = "Выздоровлений всего: ${recovered}"
        detailTodayRecoveredTextView.text = "Выздоровлений сегодня: ${todayRecovered}"
    }
}