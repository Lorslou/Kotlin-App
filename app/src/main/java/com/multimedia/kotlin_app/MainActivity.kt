package com.multimedia.kotlin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.multimedia.kotlin_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Represents the main activity of the application. It configures the user interface, navigation
 * controller, and data binding to enable navigation between fragments using the BottomNavigationView.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.frag_host_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun fragmentListener() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frag_host_container) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

    }

}