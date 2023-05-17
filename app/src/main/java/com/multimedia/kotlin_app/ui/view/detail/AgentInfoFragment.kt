package com.multimedia.kotlin_app.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.databinding.FragmentAgentInfoBinding
import com.multimedia.kotlin_app.modules.NetworkModule
import com.multimedia.kotlin_app.ui.viewmodel.AgentInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentInfoFragment : Fragment() {

    private val agentViewModel: AgentInfoViewModel by viewModels()
    private var _binding: FragmentAgentInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val AGENT_UUID = "agent_uuid"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val agentID: String = requireArguments().getString(AGENT_UUID).orEmpty()
        getAgentData(agentID)
    }

    private fun getAgentData(agentIdIntent: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val agentDataShow =
                NetworkModule.provideRetrofit().create(ValorantApiClient::class.java)
                    .getAgentId(agentIdIntent)

            if (agentDataShow.body() != null) {
                createUI(agentDataShow.body()!!)
            }
        }
    }

    private fun createUI(agentData: Agent) {
        bindingShowData(agentData)
        binding.btnFavorites.setOnClickListener { agentViewModel.switchFavoriteAgent(agentData.data.uuid) }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}