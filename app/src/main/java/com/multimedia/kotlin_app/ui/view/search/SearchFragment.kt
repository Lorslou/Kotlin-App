package com.multimedia.kotlin_app.ui.view.search

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.databinding.FragmentSearchBinding
import androidx.navigation.fragment.findNavController
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.ui.view.detail.AgentInfoFragment.Companion.AGENT_UUID
import com.multimedia.kotlin_app.ui.viewmodel.SearchFavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that displays the interface of the search screen and contains the
 * logic of the viewmodel
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchFavoritesViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AgentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        initUI()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                searchViewModel.onCreateSearch(query.orEmpty())
                return true
            }
        })

        adapter = AgentAdapter { agentID -> accessToAgentInfo(agentID) }
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAgent.adapter = adapter
    }

    private fun setupObservers() {
        searchViewModel.dataLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        searchViewModel.filteredAgentList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updateAdapter(it)
            } else {
                showAlertDialog()
                adapter.clearAdapter()
            }
        })

        //TODO
        searchViewModel.showDialog.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun accessToAgentInfo(agentID: String) {
        val bundle = bundleOf(AGENT_UUID to agentID)
        val navController = findNavController()
        navController.navigate(R.id.action_searchFragment_to_agentInfoFragment, bundle)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setMessage("Try searching something else")
            .setTitle("Not found")
            .setPositiveButton(android.R.string.ok, null)
            .create()

        alertDialog.show()
    }

}