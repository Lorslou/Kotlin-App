package com.multimedia.kotlin_app.ui.view.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.databinding.ActivityShowFavoritesBinding
import com.multimedia.kotlin_app.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShowFavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowFavoritesBinding
    private lateinit var adapter: FavoritesAdapter

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setupObservers()
        //initUI()

        //val agentDatabase = favoritesViewModel.onCreate()
        //Log.i("db", agentDatabase.toString())
    }

    /*
    private fun initUI() {
        favoritesViewModel.onCreate()
        adapter = FavoritesAdapter()
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(this)
        binding.rvAgent.adapter = adapter
    }

    private fun setupObservers(){
        favoritesViewModel.favoriteAgents.observe(this, Observer {
            if (it != null) {
                adapter.updateAdapter(it)
            } else {
                Toast.makeText(this, "Todavía no existen favoritos", Toast.LENGTH_SHORT).show()
            }
        })
    }
     */


}
