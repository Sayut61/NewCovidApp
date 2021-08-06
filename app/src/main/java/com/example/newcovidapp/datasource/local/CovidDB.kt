package com.example.newcovidapp.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo

@Database(entities = [CountryCovidInfo::class, AllCovidInfo::class], version = 1)
abstract class CovidDB : RoomDatabase(){
    abstract fun countriesInfoDao(): CountriesDao
    abstract fun allInfoDao(): AllInfoDao
}