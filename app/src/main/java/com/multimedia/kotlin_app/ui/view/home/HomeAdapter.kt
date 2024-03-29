package com.multimedia.kotlin_app.ui.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

/**
 * This is an adapter used to display all the agents in the RecyclerView of the home fragment
 */
class HomeAdapter(private val accessToAgentInfo: (String) -> Unit) :
    RecyclerView.Adapter<HomeViewHolder>() {
    private var agentList: List<AgentDataDisplay> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agent_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.render(agentList[position], accessToAgentInfo)
    }

    override fun getItemCount() = agentList.size

    fun updateAdapter(list: List<AgentDataDisplay>) {
        agentList = list
        notifyDataSetChanged()
    }


}