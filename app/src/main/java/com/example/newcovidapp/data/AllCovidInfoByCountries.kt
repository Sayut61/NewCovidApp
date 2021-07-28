package com.example.newcovidapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllCovidInfoByCountries(
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val todayRecovered: Long,
    @PrimaryKey
    val id: Int = 0
)