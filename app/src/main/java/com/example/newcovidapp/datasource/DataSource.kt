package com.example.newcovidapp.datasource

import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo

interface DataSource {
    suspend fun getAllCovidInfo(): AllCovidInfo
    suspend fun getCountriesInfo(): List<CountryCovidInfo>
}