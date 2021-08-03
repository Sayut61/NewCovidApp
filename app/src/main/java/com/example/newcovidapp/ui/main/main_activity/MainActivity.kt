package com.example.newcovidapp.ui.main.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.newcovidapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

        val configuration = AppBarConfiguration(setOf(R.id.fragmentMain, R.id.fragmentMap))
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, configuration)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.fragmentDetail)
                bottomNavigationView.visibility = View.GONE
            else
                bottomNavigationView.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
