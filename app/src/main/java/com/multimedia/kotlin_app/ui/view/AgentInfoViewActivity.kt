package com.multimedia.kotlin_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.ActivityAgentInfoViewBinding
import com.multimedia.kotlin_app.databinding.ItemAgentBinding
import com.multimedia.kotlin_app.modules.NetworkModule
import com.multimedia.kotlin_app.ui.view.favorites.ShowFavoritesFragment
import com.multimedia.kotlin_app.ui.view.home.HomeFragment
import com.multimedia.kotlin_app.ui.view.search.SearchFragment
import com.multimedia.kotlin_app.ui.viewmodel.AgentInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        agentViewModel.onCreate(agentID)
    }

    private fun setupObservers() {
        agentViewModel.favOnOff.observe(this, Observer {
            if (it) {
                binding.btnFavorites.setImageResource(R.drawable.ic_favoriteon)
            } else {
                binding.btnFavorites.setImageResource(R.drawable.ic_favoriteoff)
            }
        })

        agentViewModel.agentData.observe(this, Observer {
            createUI(it!!)
        })
    }

    private fun createUI(agentData: AgentDataDisplay) {
        bindingShowData(agentData)
        binding.btnFavorites.setOnClickListener { agentViewModel.switchFavoriteAgent(agentData.uuid) }

    }

    private fun bindingShowData(agentData: AgentDataDisplay) {
        binding.ivBackground.load(agentData.agentBackground)
        binding.ivAgentImage.load(agentData.agentInfoPortrait)
        binding.tvAgentName.text = agentData.agentName
        binding.tvDescription.text = agentData.agentDescription
        binding.ivAgentRoleImage.load(agentData.agentRole.agentRoleIcon)
        binding.tvAgentRoleName.text = agentData.agentRole.agentRoleName
        binding.ivAbility1Image.load(agentData.agentAbilities[0].abilitiesIcon)
        binding.ivAbility2Image.load(agentData.agentAbilities[1].abilitiesIcon)
        binding.ivAbility3Image.load(agentData.agentAbilities[2].abilitiesIcon)
        binding.ivAbility4Image.load(agentData.agentAbilities[3].abilitiesIcon)
        binding.tvAbility1Name.text = agentData.agentAbilities[0].abilitiesName
        binding.tvAbility2Name.text = agentData.agentAbilities[1].abilitiesName
        binding.tvAbility3Name.text = agentData.agentAbilities[2].abilitiesName
        binding.tvAbility4Name.text = agentData.agentAbilities[3].abilitiesName
    }


}