package com.multimedia.kotlin_app.ui.view.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.databinding.ActivityShowFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShowFavoritesActivity @Inject constructor(
    private val repository: AgentRepository
) : AppCompatActivity() {

    private lateinit var binding: ActivityShowFavoritesBinding
    private lateinit var adapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        adapter = FavoritesAdapter()
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(this)
        binding.rvAgent.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            adapter.updateAdapter(repository.getFavoriteAgents())
        }

    }
}
