package com.example.newcovidapp.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry

@Database(entities = [DetailCovidInfoByCountry::class, AllCovidInfoByCountries::class], version = 1)
abstract class CovidDB : RoomDatabase() {
    abstract fun countriesInfoDao(): CountriesInfoDao
    abstract fun allCovidInfoDao(): AllCovidInfoDao
}