package com.multimedia.kotlin_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.ActivityAgentInfoViewBinding
import com.multimedia.kotlin_app.databinding.ItemAgentBinding
import com.multimedia.kotlin_app.modules.NetworkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentInfoViewActivity : AppCompatActivity() {

    companion object {
        const val AGENT_UUID = "agent_uuid"
    }

    private lateinit var binding: ActivityAgentInfoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentInfoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agentID: String = intent.getStringExtra(AGENT_UUID).orEmpty()
        getAgentData(agentID)

    }

    private fun getAgentData(agentIdIntent: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val agentDataShow =
                NetworkModule.provideRetrofit().create(ValorantApiClient::class.java)
                    .getAgentId(agentIdIntent)

            if (agentDataShow.body() != null) {
                runOnUiThread { createUI(agentDataShow.body()!!) }
            }
        }
    }

    private fun createUI(agentData: Agent) {
        binding.ivBackground.load(agentData.data.agentBackground)
        binding.ivAgentImage.load(agentData.data.agentInfoPortrait)
        binding.tvAgentName.text = agentData.data.agentName
        binding.tvDescription.text = agentData.data.agentDescription
        binding.ivAgentRoleImage.load(agentData.data.agentRole.agentRoleIcon)
        binding.tvAgentRoleName.text = agentData.data.agentRole.agentRoleName
    }
}