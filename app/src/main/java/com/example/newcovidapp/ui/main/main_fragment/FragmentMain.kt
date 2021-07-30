package com.example.newcovidapp.ui.main.main_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.SearchView
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showAllInfoByCountries()
        viewModel.allCovidInfoByCountryLiveData.observe(viewLifecycleOwner){allCovidInfo->
            showAllCovidInfoByAllCountries(allCovidInfo)
        }
        viewModel.countryNameLiveData.observe(viewLifecycleOwner){allNameCounties->
            showAllCountriesName(allNameCounties)
        }
        viewModel.exceptionLiveData.observe(viewLifecycleOwner){exception->
            showError(exception)
        }
        viewModel.progressBarLiveData.observe(viewLifecycleOwner){progressBar->
            if(progressBar == true) showProgressBar()
            else hideProgressBar()
        }

        searchWiev.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.filterByCountry(newText)
                }
                return true
            }
        })
    }
    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
    @SuppressLint("SetTextI18n")
    private fun showAllCovidInfoByAllCountries(getAllInfoCovid: AllCovidInfoByCountries){
        allCasesTextView.text = "Случаев всего: ${getAllInfoCovid.cases}"
        allTodayCasesTextView.text = "Случаев сегодня: ${getAllInfoCovid.todayCases}"
        allDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.deaths}"
        allTodayDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.todayDeaths}"
        allRecoveredTextView.text = "Выздоравлений всего: ${getAllInfoCovid.recovered}"
        allTodayRecoveredTextView.text = "Выздоравлений сегодня: ${getAllInfoCovid.todayRecovered}"
    }
    private fun showAllCountriesName(getAllNameCountries: List<DetailCovidInfoByCountry>){
        val adapter = CovidAdapter(getAllNameCountries, this)
        recyclerViewCountries.adapter = adapter
    }
    private fun showError(ex: Exception){
        Toast.makeText(requireContext(), "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
    }
    private fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }
    override fun onCountryClick(country: DetailCovidInfoByCountry) {
        val action = FragmentMainDirections.actionFragmentMainToFragmentDetail(country.country, country.cases, country.todayCases,
        country.deaths, country.todayDeaths, country.recovered, country.todayRecovered)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh -> {
                viewModel.showAllInfoByCountries()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}