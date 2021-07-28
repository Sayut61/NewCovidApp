package com.example.newcovidapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countriesInfo")
data class DetailCovidInfoByCountry(
    @PrimaryKey
    val country: String,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val todayRecovered: Long
)