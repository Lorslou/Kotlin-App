package com.multimedia.kotlin_app.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.AgentAdapter
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.ActivityAgentSearchViewBinding
import com.multimedia.kotlin_app.modules.NetworkModule
import com.multimedia.kotlin_app.ui.view.AgentInfoViewActivity.Companion.AGENT_UUID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@AndroidEntryPoint
class AgentSearchViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentSearchViewBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: AgentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentSearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = NetworkModule.provideRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByAgentId(query.orEmpty()) //me devuelves un agent existente o todos los que haya (en caso null)

                return false //SIEMPRE se retorna en false
            }

            //esta fun se llama cada vez que escribamos
            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = AgentAdapter { agentID -> accessToAgentInfo(agentID) }
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(this)
        binding.rvAgent.adapter = adapter
    }

    private fun searchByAgentId(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val service = retrofit.create(ValorantApiClient::class.java).getAgentId(query)

            if (service.isSuccessful) {
                Log.i("lorena", "funciona :)")

                val response: Agent? = service.body()

                if (response != null) {
                    Log.i("lorena", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.data)
                        binding.progressBar.isVisible = false
                    }
                }

            } else {
                Log.i("lorena", "NO funciona :)")
            }
        }
    }

    private fun accessToAgentInfo(agentID: String) {
        val intent = Intent(this, AgentInfoViewActivity::class.java)
        intent.putExtra(AGENT_UUID, agentID)
        startActivity(intent)
    }

}