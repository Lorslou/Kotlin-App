package com.multimedia.kotlin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.multimedia.kotlin_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentListener()
    }

    private fun linkFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContainer,fragment)
        fragmentTransaction.commit()
    }

    private fun fragmentListener() {
        binding.bottomNavigationView.setOnItemReselectedListener {
            when(it.itemId) {
                binding.bottomNavigationView.menu.getItem(0).itemId -> linkFragment(SearchFragment())
                binding.bottomNavigationView.menu.getItem(1).itemId -> linkFragment(HomeFragment())
                binding.bottomNavigationView.menu.getItem(2).itemId -> linkFragment(ShowFavoritesFragment())
            }
        }
    }

}