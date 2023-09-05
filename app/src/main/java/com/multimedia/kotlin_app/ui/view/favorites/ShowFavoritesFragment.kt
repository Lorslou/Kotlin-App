package com.multimedia.kotlin_app.ui.view.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.databinding.FragmentShowFavoritesBinding
import com.multimedia.kotlin_app.ui.view.detail.AgentInfoFragment
import com.multimedia.kotlin_app.ui.viewmodel.SearchFavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that displays the interface of the favorite agents screen and contains the
 *  logic of the viewmodel
 */
@AndroidEntryPoint
class ShowFavoritesFragment : Fragment() {

    private val favoritesViewModel: SearchFavoritesViewModel by viewModels()

    private var _binding: FragmentShowFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        initUI()
    }

    private fun initUI() {
        favoritesViewModel.onCreateFavoritesView()
        adapter = FavoritesAdapter {agentID -> accessToAgentInfo(agentID)}
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAgent.adapter = adapter
    }

    private fun setupObservers() {
        favoritesViewModel.favoriteAgents.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updateAdapter(it)
            } else {
                Toast.makeText(requireContext(), "Todavía no existen favoritos", Toast.LENGTH_SHORT) //TODO
                    .show()
            }
        })

    }

    private fun accessToAgentInfo(agentID: String) {
        val bundle = bundleOf(AgentInfoFragment.AGENT_UUID to agentID)
        val navController = findNavController()
        navController.navigate(R.id.action_favoritesFragment_to_agentInfoFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}