package com.example.countries.view.activits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.countries.R
import com.example.countries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController  : NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Operation work for fragment.
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController   = navHostFragment.navController

        // Operation work for action bar on fragment page.
        appBarConfiguration =AppBarConfiguration(setOf(R.id.mainFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        // Work on hide and show action bar on fragment page.
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id){

                else -> supportActionBar!!.show()
            }
        }
    }
}