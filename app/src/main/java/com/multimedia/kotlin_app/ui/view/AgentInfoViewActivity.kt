package com.multimedia.kotlin_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import coil.load
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.ActivityAgentInfoViewBinding
import com.multimedia.kotlin_app.databinding.ItemAgentBinding
import com.multimedia.kotlin_app.modules.NetworkModule
import com.multimedia.kotlin_app.ui.viewmodel.AgentInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentInfoViewActivity : AppCompatActivity() {

    private val agentViewModel: AgentInfoViewModel by viewModels()

    companion object {
        const val AGENT_UUID = "agent_uuid"
    }

    private lateinit var binding: ActivityAgentInfoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentInfoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        val agentID: String = intent.getStringExtra(AGENT_UUID).orEmpty()
        getAgentData(agentID)


    }

    private fun setupObservers() {
        agentViewModel.favOnOff.observe(this, Observer {

        })

        agentViewModel.favoriteAgents.observe(this, Observer {

        })
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
        bindingShowData(agentData)
        binding.btnFavorites.setOnClickListener { agentViewModel.turnOffOnFavorite(agentData.data.uuid) }

    }

    private fun bindingShowData(agentData: Agent) {
        binding.ivBackground.load(agentData.data.agentBackground)
        binding.ivAgentImage.load(agentData.data.agentInfoPortrait)
        binding.tvAgentName.text = agentData.data.agentName
        binding.tvDescription.text = agentData.data.agentDescription
        binding.ivAgentRoleImage.load(agentData.data.agentRole.agentRoleIcon)
        binding.tvAgentRoleName.text = agentData.data.agentRole.agentRoleName
        binding.ivAbility1Image.load(agentData.data.agentAbilities[0].abilitiesIcon)
        binding.ivAbility2Image.load(agentData.data.agentAbilities[1].abilitiesIcon)
        binding.ivAbility3Image.load(agentData.data.agentAbilities[2].abilitiesIcon)
        binding.ivAbility4Image.load(agentData.data.agentAbilities[3].abilitiesIcon)
        binding.tvAbility1Name.text = agentData.data.agentAbilities[0].abilitiesName
        binding.tvAbility2Name.text = agentData.data.agentAbilities[1].abilitiesName
        binding.tvAbility3Name.text = agentData.data.agentAbilities[2].abilitiesName
        binding.tvAbility4Name.text = agentData.data.agentAbilities[3].abilitiesName
    }


}