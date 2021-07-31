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
import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception


class FragmentMain : Fragment(), CovidAdapterListener {
    private val viewModel: FragmentMainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshAllAndCountriesInfo()
        viewModel.allCovidInfoLiveData.observe(viewLifecycleOwner) { allCovidInfo ->
            showAllCovidInfoByAllCountries(allCovidInfo)
        }
        viewModel.countryNameLiveData.observe(viewLifecycleOwner) { allNameCounties ->
            showAllCountriesName(allNameCounties)
        }
        viewModel.exceptionLiveData.observe(viewLifecycleOwner) { exception ->
            showError(exception)
        }
        viewModel.progressBarLiveData.observe(viewLifecycleOwner) { progressBar ->
            if (progressBar == true) showProgressBar()
            else hideProgressBar()
        }

        searchViewView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.changeFilterText(newText)
                return true
            }
        })

    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun showAllCovidInfoByAllCountries(getAllInfoCovid: AllCovidInfo) {
        allCasesTextView.text = "Случаев всего: ${getAllInfoCovid.cases}"
        allTodayCasesTextView.text = "Случаев сегодня: ${getAllInfoCovid.todayCases}"
        allDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.deaths}"
        allTodayDeathsTextView.text = "Смертей всего: ${getAllInfoCovid.todayDeaths}"
        allRecoveredTextView.text = "Выздоравлений всего: ${getAllInfoCovid.recovered}"
        allTodayRecoveredTextView.text = "Выздоравлений сегодня: ${getAllInfoCovid.todayRecovered}"
    }

    private fun showAllCountriesName(getAllNameCountryCovidInfos: List<CountryCovidInfo>) {
        val adapter = CovidAdapter(getAllNameCountryCovidInfos, this)
        recyclerViewCountries.adapter = adapter
    }

    private fun showError(ex: Exception) {
        Toast.makeText(requireContext(), "Ошибка - ${ex.message}", Toast.LENGTH_LONG).show()
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onCountryClick(countryCovidInfo: CountryCovidInfo) {
        val action = FragmentMainDirections.actionFragmentMainToFragmentDetail(
            countryCovidInfo.country, countryCovidInfo.cases, countryCovidInfo.todayCases,
            countryCovidInfo.deaths, countryCovidInfo.todayDeaths, countryCovidInfo.recovered, countryCovidInfo.todayRecovered
        )
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                viewModel.refreshAllAndCountriesInfo()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}