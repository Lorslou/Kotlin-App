package com.multimedia.kotlin_app.ui.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

class HomeAdapter(
    private var agentList: List<AgentDataDisplay> = emptyList()
) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.render(agentList[position])
    }

    override fun getItemCount() = agentList.size

    fun updateAdapter(list: List<AgentDataDisplay>) {
        agentList = list
        notifyDataSetChanged()
    }

    fun clearAdapter(){
        agentList = emptyList()
        notifyDataSetChanged()
    }

}