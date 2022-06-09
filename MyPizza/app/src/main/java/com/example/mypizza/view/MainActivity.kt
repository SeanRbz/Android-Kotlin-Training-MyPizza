package com.example.mypizza.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mypizza.R
import com.example.mypizza.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        navController = findNavController(R.id.nav_host_fragment_home)

        navController.setGraph(R.navigation.app_nav)

        appBarConfiguration =AppBarConfiguration(setOf(R.id.MainLandingPage))

        setupActionBarWithNavController(navController,appBarConfiguration)
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_home).navigateUp(appBarConfiguration)
    }




}