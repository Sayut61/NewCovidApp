package com.example.newcovidapp.ui.main.map_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.newcovidapp.App
import com.example.newcovidapp.R
import com.example.newcovidapp.data.CountryCovidInfo
import com.example.newcovidapp.datasource.local.CovidDB
import com.example.newcovidapp.ui.adapters.CovidAdapter
import com.example.newcovidapp.ui.adapters.CovidAdapterListener
import kotlinx.android.synthetic.main.fragment_favorite.*

class FragmentFavorite : Fragment(), CovidAdapterListener {
    val covidInfoDao = App.db.countriesInfoDao()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoriteCountries = covidInfoDao.getAllInfo()
        val adapter = CovidAdapter(favoriteCountries, this)
        favoriteCountriesRecyclerView.adapter = adapter
    }

    override fun onCountryClick(countryCovidInfo: CountryCovidInfo) {

    }
}