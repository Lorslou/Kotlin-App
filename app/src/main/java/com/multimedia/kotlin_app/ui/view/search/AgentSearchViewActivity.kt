package com.multimedia.kotlin_app.ui.view.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.ui.viewmodel.AgentSearchViewModel
import com.multimedia.kotlin_app.databinding.ActivityAgentSearchViewBinding
import com.multimedia.kotlin_app.ui.view.AgentInfoViewActivity
import com.multimedia.kotlin_app.ui.view.AgentInfoViewActivity.Companion.AGENT_UUID
import com.multimedia.kotlin_app.ui.view.favorites.ShowFavoritesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentSearchViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentSearchViewBinding
    private lateinit var adapter: AgentAdapter

    private val agentviewmodel : AgentSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentSearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                agentviewmodel.onCreate(query.orEmpty())

                return false
            }
            override fun onQueryTextChange(newText: String?) = false //esta fun se llama cada vez que escribamos
        })


        adapter = AgentAdapter { agentID -> accessToAgentInfo(agentID) }
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(this)
        binding.rvAgent.adapter = adapter
        binding.btnToFavorites.setOnClickListener { accessToFavorites() }
    }

    private fun setupObservers(){
        agentviewmodel.dataLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        agentviewmodel.agentDisplay.observe(this, Observer {
            if (it != null) {
                adapter.updateAdapter(it)
            } else {
                Toast.makeText(this, "El agente introducido no existe", Toast.LENGTH_LONG).show()
                adapter.clearAdapter()
            }

        })
    }

    private fun accessToAgentInfo(agentID: String) {
        val intent = Intent(this, AgentInfoViewActivity::class.java)
        intent.putExtra(AGENT_UUID, agentID)
        startActivity(intent)
    }

    private fun accessToFavorites() {
        val intent = Intent(this, ShowFavoritesActivity::class.java)
        startActivity(intent)
    }


}