package com.example.newcovidapp.datasource.local

import androidx.room.*
import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countriesInfo")
    fun getAllInfo(): List<CountryCovidInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countryCovidInfo: CountryCovidInfo)

    @Delete
    fun delete(countryCovidInfo: CountryCovidInfo)
}