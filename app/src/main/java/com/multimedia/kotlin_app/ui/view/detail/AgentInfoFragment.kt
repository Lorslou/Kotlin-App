package com.multimedia.kotlin_app.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.FragmentAgentInfoBinding
import com.multimedia.kotlin_app.ui.viewmodel.AgentInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentInfoFragment : Fragment() {

    private val agentViewModel: AgentInfoViewModel by viewModels()

    private var _binding: FragmentAgentInfoBinding? = null
    private val binding get() = _binding!!
    private var agentUUID: String? = null


    companion object {
        const val AGENT_UUID = "agent_uuid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            agentUUID = it.getString(AGENT_UUID)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        agentViewModel.onCreate(agentUUID!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupObservers() {
        agentViewModel.favOnOff.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.btnFavorites.setImageResource(R.drawable.ic_favoriteon)
            } else {
                binding.btnFavorites.setImageResource(R.drawable.ic_favoriteoff)
            }
        })

        agentViewModel.agentData.observe(viewLifecycleOwner, Observer {
            createUI(it!!)
        })

        agentViewModel.goBack.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

    private fun createUI(agentData: AgentDataDisplay) {
        bindingShowData(agentData)
        binding.btnFavorites.setOnClickListener { agentViewModel.switchFavoriteAgent(agentData.uuid) }
        binding.ibGoBack.setOnClickListener { agentViewModel.goBackToSearch() }
    }

    //TODO SWAP TO RECYCLERVIEW
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}