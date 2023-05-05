package com.multimedia.kotlin_app.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.multimedia.kotlin_app.MainActivity
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.database.entities.AllAgentsEntity
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ActivityWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeActivity @Inject constructor(
    private val repository: AgentRepository
) : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO el get started vincularía a login si lo aplicase, no a la view del buscador
        binding.btnAccessToApp.setOnClickListener { accessToMainActivity() }
    }

    private fun accessToMainActivity() {
        val intent = Intent(this, AgentSearchViewActivity::class.java)
        startActivity(intent)
    }

    private fun loadAgentsToDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val agentEntities = mutableListOf<AllAgentsEntity>()
            val agents: Agent? = repository.getAllAgents()
            val agentDataList = listOf(agents?.data)

            for (agentData in agentDataList) {
                val listOfAgents = AllAgentsEntity(
                    agents?.data?.uuid.orEmpty(),
                    agents?.data?.agentName.orEmpty()
                )
                repository.addAgentsToDb(listOfAgents)
            }

        }
    }
}