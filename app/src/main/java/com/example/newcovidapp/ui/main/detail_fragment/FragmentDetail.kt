package com.example.newcovidapp.ui.main.detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import kotlinx.android.synthetic.main.fragment_detail.*


class FragmentDetail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: CountryDetailViewModel by viewModels()
        setContentView(R.layout.fragment_detail)
        val countryName = arguments?.getString("nameCountry")
        viewModel.showDetailInfoByCountry(countryName!!)
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