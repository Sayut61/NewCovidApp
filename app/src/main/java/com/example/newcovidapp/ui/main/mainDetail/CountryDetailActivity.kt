package com.example.newcovidapp.ui.main.mainDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.newcovidapp.R
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.datasource.service
import com.example.newcovidapp.ui.main.mainAll.MainViewInterface
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.coroutines.launch
import java.lang.Exception

class CountryDetailActivity : AppCompatActivity(), CountryDetailViewInterface{
    val presenter = CountryDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val countryName = intent.getStringExtra("nameCountry")

        presenter.onViewCreated(countryName!!)
    }
    override fun showDetailInfoByCountry(country: DetailCovidInfoByCountry){
        detailNameCountryTextView.text = country.country
        detailCasesTextView.text = "Случаев всего: ${country.cases}"
        detailTodayCasesTextView.text = "Случаев сегодня: ${country.todayCases}"
        detailDeathsTextView.text = "Смертей всего: ${country.deaths}"
        detailTodayDeathsTextView.text = "Смертей сегодня: ${country.todayDeaths}"
        detailRecoveredTextView.text = "Выздоровлений всего: ${country.recovered}"
        detailTodayRecoveredTextView.text = "Выздоровлений сегодня: ${country.todayRecovered}"
    }
}