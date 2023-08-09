package com.multimedia.kotlin_app.ui.view.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ItemAgentBinding

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAgentBinding.bind(view)

    fun render(agentDataDisplay: AgentDataDisplay) {
        binding.tvAgentName.text = agentDataDisplay.agentName
        binding.ivAgent.load(agentDataDisplay.agentIcon)

    }
}