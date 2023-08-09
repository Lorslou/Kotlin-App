package com.multimedia.kotlin_app.ui.view.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ItemAgentBinding

/**
 * This class is responsible for binding the data from AgentDataDisplay to the view elements
 * in each item of the RecyclerView in the agent list of the search
 */
class AgentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // The adapter's list passes an item to the ViewHolder (AgentDataDisplay)

    private val binding = ItemAgentBinding.bind(view)

    fun render(agentDataDisplay: AgentDataDisplay, accessToAgentInfo: (String) -> Unit) {
        binding.tvAgentName.text = agentDataDisplay.agentName

        binding.ivAgent.load(agentDataDisplay.agentIcon)
        binding.root.setOnClickListener { accessToAgentInfo(agentDataDisplay.uuid) }

    }
}