package com.example.newcovidapp.data

class CountryCovidInfo(
    val country: String,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val todayRecovered: Long
)