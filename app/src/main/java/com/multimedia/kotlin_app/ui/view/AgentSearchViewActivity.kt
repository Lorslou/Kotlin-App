package com.multimedia.kotlin_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.ui.viewmodel.AgentSearchViewModel
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.ActivityAgentSearchViewBinding
import com.multimedia.kotlin_app.modules.NetworkModule
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

    private val agentviewmodel : AgentSearchViewModel by viewModels()

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
                //searchByAgentId(query.orEmpty()) //me devuelves un agent existente o vacío
                agentviewmodel.searchByAgentId(query.orEmpty())

                return false //SIEMPRE se retorna en false
            }
            override fun onQueryTextChange(newText: String?) = false //esta fun se llama cada vez que escribamos
        })

        //viewModel nos informa a nosotros
        agentviewmodel.dataLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

        agentviewmodel.agentModel.observe(this, Observer {

        })



        //adapter = AgentAdapter { agentID -> accessToAgentInfo(agentID) }
        //activity informa al viewModel de que el usuario está realizando una acción
        adapter = AgentAdapter { agentID -> agentviewmodel.accessToAgentInfo(agentID, this) }
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(this)
        binding.rvAgent.adapter = adapter


    }


    // viewModel.onCreate()

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
                } else {
                    //TODO DEVOLVER LISTA DE AGENTES ENTERA SI NO ENCUENTRA ID
                }

            } else {
                Log.i("lorena", "NO funciona :)")
            }
        }
    }

    /*
    private fun accessToAgentInfo(agentID: String) {
        val intent = Intent(this, AgentInfoViewActivity::class.java)
        intent.putExtra(AGENT_UUID, agentID)
        startActivity(intent)
    }
     */



}