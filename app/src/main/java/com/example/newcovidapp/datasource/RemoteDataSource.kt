package com.example.newcovidapp.datasource

import com.example.newcovidapp.data.AllCovidInfo
import com.example.newcovidapp.data.CountryCovidInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



object RemoteDataSource : DataSource {
    interface RestCovidStatisticsAPI {
        @GET("all")
        suspend fun getAllCovidInfo(): AllCovidInfo

        @GET("countries")
        suspend fun getCountriesInfo(): List<CountryCovidInfo>
    }


    private var retrofit = Retrofit.Builder()
        .baseUrl("https://disease.sh/v3/covid-19/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private var service = retrofit.create(RestCovidStatisticsAPI::class.java)

    override suspend fun getAllCovidInfo(): AllCovidInfo{
        return service.getAllCovidInfo()
    }

    override suspend fun getCountriesInfo(): List<CountryCovidInfo>{
        return service.getCountriesInfo()
    }
}

