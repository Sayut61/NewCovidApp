package com.example.newcovidapp.ui.main.map_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.newcovidapp.R
import com.example.newcovidapp.datasource.local.CovidDB

class FragmentMap : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)

        val db = Room.databaseBuilder(requireContext(), CovidDB::class.java, "db").build()
        val covidInfoDao = db.countriesInfoDao()
    }
}