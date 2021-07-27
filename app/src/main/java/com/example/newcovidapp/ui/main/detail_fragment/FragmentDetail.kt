package com.example.newcovidapp.ui.main.detail_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newcovidapp.R
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import kotlinx.android.synthetic.main.fragment_detail.*


class FragmentDetail : Fragment() {
    private val args: FragmentDetailArgs by navArgs()
    private val viewModel: FragmentCountryDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryName = args.country
        viewModel.showDetailInfoByCountry(countryName)
        viewModel.detailCovidInfoByCountryLiveData.observeForever{
            showDetailInfoByCountry(it)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showDetailInfoByCountry(country: DetailCovidInfoByCountry){
        detailNameCountryTextView.text = country.country
        detailCasesTextView.text = "Случаев всего: ${country.cases}"
        detailTodayCasesTextView.text = "Случаев сегодня: ${country.todayCases}"
        detailDeathsTextView.text = "Смертей всего: ${country.deaths}"
        detailTodayDeathsTextView.text = "Смертей сегодня: ${country.todayDeaths}"
        detailRecoveredTextView.text = "Выздоровлений всего: ${country.recovered}"
        detailTodayRecoveredTextView.text = "Выздоровлений сегодня: ${country.todayRecovered}"
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.top_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.refresh ->{
//                viewModel.showDetailInfoByCountry(countryName = String())
//            }
//        }
//        return super.onContextItemSelected(item)
//    }
}