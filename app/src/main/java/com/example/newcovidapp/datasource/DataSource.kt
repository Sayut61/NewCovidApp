package com.example.newcovidapp.datasource

import com.example.newcovidapp.data.AllCovidInfoByCountries
import com.example.newcovidapp.data.DetailCovidInfoByCountry
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RestCovidStatisticsAPI {
    @GET("all")
    suspend fun getAllInfoByAllCountry(): AllCovidInfoByCountries

    @GET("countries")
    suspend fun getCountryName(): List<DetailCovidInfoByCountry>

    @GET("countries")
    suspend fun getDetailInfoByCountry(): DetailCovidInfoByCountry
}


var retrofit = Retrofit.Builder()
    .baseUrl("https://disease.sh/v3/covid-19/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


var service = retrofit.create(RestCovidStatisticsAPI::class.java)