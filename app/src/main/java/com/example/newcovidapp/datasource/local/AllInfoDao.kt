package com.example.newcovidapp.datasource.local

import androidx.room.*
import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo

@Dao
interface AllInfoDao {
    @Query("SELECT * FROM allInfo")
    fun getAllInfo(): List<AllCovidInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countryCovidInfo: AllCovidInfo)

    @Delete
    fun delete(countryCovidInfo: AllCovidInfo)
}