package com.example.newcovidapp.ui.main.main_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newcovidapp.R
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


class FragmentMain : Fragment(), CovidAdapterListener {
    private val viewModel: FragmentMainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showAllInfoByCountries()
        viewModel.allCovidInfoByCountryLiveData.observe(viewLifecycleOwner) { allCovidInfo ->
            showAllCovidInfoByAllCountries(allCovidInfo)
        }
        viewModel.countriesInfoLiveData.observe(viewLifecycleOwner) { allNameCounties ->
            showAllCountriesName(allNameCounties)
        }
        viewModel.exceptionLiveData.observe(viewLifecycleOwner) { exception ->
            showError(exception)
        }
        viewModel.progressBarLiveData.observe(viewLifecycleOwner) { progressBar ->
            if (progressBar == true) showProgressBar()
            else hideProgressBar()
        }

        /*sortTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.byNameChip ->{
                    viewModel.changeSortType(FragmentMainViewModel.SortType.BY_NAME)
                }
                R.id.byCasesChip ->{
                    viewModel.changeSortType(FragmentMainViewModel.SortType.BY_CASES)
                }
            }
        }*/

        refreshButton.setOnClickListener {
            viewModel.showAllInfoByCountries()
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 ->{
                        viewModel.changeSortType(FragmentMainViewModel.SortType.BY_NAME)
                    }
                    1 ->{
                        viewModel.changeSortType(FragmentMainViewModel.SortType.BY_CASES)
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun showAllCovidInfoByAllCountries(getAllInfoCovid: AllCovidInfoByCountries) {
        allCasesTextView.text = "Случаев всего: ${getAllInfoCovid.cases}"
        allTodayCasesTextView.text = "Случаев сегодня: ${getAllInfoCovid.todayCases}"
        allDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.deaths}"
        allTodayDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.todayDeaths}"
        allRecoveredTextView.text = "Выздоравлений всего: ${getAllInfoCovid.recovered}"
        allTodayRecoveredTextView.text = "Выздоравлений сегодня: ${getAllInfoCovid.todayRecovered}"
    }

    private fun showAllCountriesName(getAllNameCountries: List<DetailCovidInfoByCountry>) {
        val adapter = CovidAdapter(getAllNameCountries, this)
        recyclerViewCountries.adapter = adapter
    }

    private fun showError(ex: Exception) {
        Toast.makeText(requireContext(), "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onCountryClick(country: DetailCovidInfoByCountry) {
        val action = FragmentMainDirections.actionFragmentMainToFragmentDetail(
            country.country, country.cases, country.todayCases,
            country.deaths, country.todayDeaths, country.recovered, country.todayRecovered
        )
        findNavController().navigate(action)
    }
}