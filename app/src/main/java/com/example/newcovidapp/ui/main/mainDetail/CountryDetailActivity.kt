package com.example.newcovidapp.ui.main.mainDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import kotlinx.android.synthetic.main.activity_country_detail.*

class CountryDetailActivity : AppCompatActivity(){
    private val viewModel: CountryDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)
        val countryName = intent.getStringExtra("nameCountry")
        viewModel.onViewCreated(countryName!!)
        viewModel.detailCovidInfoByCountryLiveData.observeForever{
            showDetailInfoByCountry(it)
        }
    }
    fun showDetailInfoByCountry(country: DetailCovidInfoByCountry){
        detailNameCountryTextView.text = country.country
        detailCasesTextView.text = "Случаев всего: ${country.cases}"
        detailTodayCasesTextView.text = "Случаев сегодня: ${country.todayCases}"
        detailDeathsTextView.text = "Смертей всего: ${country.deaths}"
        detailTodayDeathsTextView.text = "Смертей сегодня: ${country.todayDeaths}"
        detailRecoveredTextView.text = "Выздоровлений всего: ${country.recovered}"
        detailTodayRecoveredTextView.text = "Выздоровлений сегодня: ${country.todayRecovered}"
    }
}