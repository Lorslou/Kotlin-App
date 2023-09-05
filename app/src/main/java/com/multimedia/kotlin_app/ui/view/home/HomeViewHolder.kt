package com.multimedia.kotlin_app.ui.view.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ItemAgentBinding
import com.multimedia.kotlin_app.databinding.ItemAgentHomeBinding

/**
 * This class is responsible for binding the data from AgentDataDisplay to the view elements
 * in each item of the RecyclerView on the home screen
 */
class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAgentHomeBinding.bind(view)

    fun render(agentDataDisplay: AgentDataDisplay, accessToAgentInfo: (String) -> Unit) {
        binding.tvAgentName.text = agentDataDisplay.agentName
        binding.ivAgent.load(agentDataDisplay.agentIcon)
        binding.root.setOnClickListener { accessToAgentInfo(agentDataDisplay.uuid) }

    }
}