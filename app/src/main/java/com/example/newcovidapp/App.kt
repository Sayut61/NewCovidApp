package com.example.newcovidapp

import android.app.Application
import androidx.room.Room
import com.example.newcovidapp.datasource.local.CovidDB

class App: Application() {
    companion object{
        lateinit var db: CovidDB
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, CovidDB::class.java, "db").build()
    }
}