package com.multimedia.kotlin_app.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ItemAgentBinding

//El listado del adapter le pasa un item al ViewHolder (AgentDataDisplay)
class AgentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAgentBinding.bind(view)

    fun render(agentDataDisplay: AgentDataDisplay, accessToAgentInfo: (String) -> Unit) {
        binding.tvAgentName.text = agentDataDisplay.agentName

        binding.ivAgent.load(agentDataDisplay.agentIcon)
        binding.root.setOnClickListener { accessToAgentInfo(agentDataDisplay.uuid) }

    }
}