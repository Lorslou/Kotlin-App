package com.multimedia.kotlin_app.ui.view.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs

/**
 * This is an adapter used to display favorites information in the RecyclerView
 * of the favorites fragment
 */
class FavoritesAdapter(private val accessToAgentInfo: (String) -> Unit) :
    RecyclerView.Adapter<FavoritesViewHolder>() {
    private var favAgentsList: List<AgentEntityFavs> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.render(favAgentsList[position], accessToAgentInfo)
    }

    override fun getItemCount() = favAgentsList.size


    fun updateAdapter(list: List<AgentEntityFavs>) {
        favAgentsList = list
        notifyDataSetChanged()
    }


}