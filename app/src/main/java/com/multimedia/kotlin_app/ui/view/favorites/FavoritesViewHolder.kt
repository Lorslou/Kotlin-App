package com.multimedia.kotlin_app.ui.view.favorites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.databinding.ItemAgentBinding

/**
 * This class is responsible for binding the data from AgentEntityFavs to the view elements
 * in each item of the RecyclerView in the list of favorite agents
 */
class FavoritesViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAgentBinding.bind(view)

    fun render(agentFav: AgentEntityFavs, accessToAgentInfo: (String) -> Unit) {
        binding.tvAgentName.text = agentFav.agentName
        binding.ivAgent.load(agentFav.agentIcon)
        binding.root.setOnClickListener { accessToAgentInfo(agentFav.uuid) }
    }
}