package com.example.newcovidapp.ui.main.main_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.newcovidapp.R
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


class FragmentMain : androidx.fragment.app.Fragment(), CovidAdapterListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel: MainActivityViewModel by viewModels()
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
        val adapter = CovidAdapter(getAllNameCountries, R.layout.fragment_main)
        recyclerViewCountries.adapter = adapter
    }
    fun showError(ex: Exception){
        Toast.makeText(requireContext(), "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
    }
    fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }

    override fun onCountryClick(country: DetailCovidInfoByCountry) {
        val intent = Intent()
        intent.putExtra("nameCountry", country.country)
        startActivity(intent)
    }
}