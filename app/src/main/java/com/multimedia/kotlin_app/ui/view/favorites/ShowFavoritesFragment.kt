package com.multimedia.kotlin_app.ui.view.favorites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.databinding.FragmentSearchBinding
import com.multimedia.kotlin_app.databinding.FragmentShowFavoritesBinding
import com.multimedia.kotlin_app.ui.view.AgentInfoViewActivity
import com.multimedia.kotlin_app.ui.viewmodel.AgentSearchViewModel
import com.multimedia.kotlin_app.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowFavoritesFragment : Fragment() {

    private val favoritesViewModel by viewModels<FavoritesViewModel>()

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
        favoritesViewModel.onCreate()
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
                Toast.makeText(requireContext(), "Todavía no existen favoritos", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun accessToAgentInfo(agentID: String) {
        val intent = Intent(requireContext(), AgentInfoViewActivity::class.java)
        intent.putExtra(AgentInfoViewActivity.AGENT_UUID, agentID)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}