package com.multimedia.kotlin_app.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.multimedia.kotlin_app.databinding.FragmentHomeBinding
import com.multimedia.kotlin_app.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that displays the interface of the home screen and contains the
 *  logic of the viewmodel
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        initUI()
    }

    private fun setupObservers() {
        homeViewModel.agentsDisplay.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updateAdapter(it)
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun initUI() {
        homeViewModel.onCreateHomeView()
        adapter = HomeAdapter()
        binding.rvAgent.setHasFixedSize(true)
        binding.rvAgent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAgent.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
