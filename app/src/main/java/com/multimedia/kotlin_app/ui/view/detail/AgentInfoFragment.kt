package com.multimedia.kotlin_app.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.databinding.FragmentAgentInfoBinding
import com.multimedia.kotlin_app.ui.viewmodel.AgentInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentInfoFragment : Fragment() {

    private val agentViewModel: AgentInfoViewModel by viewModels()

    private var _binding: FragmentAgentInfoBinding? = null
    private val binding get() = _binding!!
    private var agentUUID: String? = null
    private lateinit var  adapter: AgentInfoAdapter


    companion object {
        const val AGENT_UUID = "agent_uuid"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        agentViewModel.onCreate(agentUUID!!)
        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            agentUUID = it.getString(AGENT_UUID)
        }

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

            adapter.updateAdapter(listOf(it!!))
        })


        agentViewModel.goBack.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

    private fun initUI() {
        adapter = AgentInfoAdapter()
        binding.rvAgentDetail.setHasFixedSize(true)
        binding.rvAgentDetail.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAgentDetail.adapter = adapter
        //binding.btnFavorites.setOnClickListener { agentViewModel.switchFavoriteAgent(agentData.uuid) }
        binding.ibGoBack.setOnClickListener { agentViewModel.goBackToSearch() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}