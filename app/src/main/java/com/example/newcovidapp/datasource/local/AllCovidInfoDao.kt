package com.example.newcovidapp.datasource.local

import androidx.room.*
import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry

@Dao
interface AllCovidInfoDao {
    @Query("SELECT * FROM allcovidinfobycountries")
    fun getAllInfo(): List<AllCovidInfoByCountries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(info: AllCovidInfoByCountries)

    @Delete
    fun delete(info: AllCovidInfoByCountries)
}