package com.example.newcovidapp.datasource.local

import androidx.room.*
import com.example.newcovidapp.data.DetailCovidInfoByCountry

@Dao
interface CountriesInfoDao {
    @Query("SELECT * FROM countriesInfo")
    fun getAllInfo(): List<DetailCovidInfoByCountry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countryInfo: DetailCovidInfoByCountry)

    @Delete
    fun delete(countryInfo: DetailCovidInfoByCountry)

    @Query("SELECT * FROM countriesInfo WHERE country=:name")
    fun getCountryInfoByName(name: String): List<DetailCovidInfoByCountry>
}