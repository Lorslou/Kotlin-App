package com.multimedia.kotlin_app.ui.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.ui.view.home.HomeViewHolder


class AgentInfoAdapter(
    private var agentInfo: List<AgentDataDisplay> = emptyList()
) : RecyclerView.Adapter<AgentInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentInfoViewHolder {
        return AgentInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_info_agent_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AgentInfoViewHolder, position: Int) {
        holder.render(agentInfo[position])
    }

    override fun getItemCount() = agentInfo.size

    fun updateAdapter(list: List<AgentDataDisplay>) {
        agentInfo = list
        notifyDataSetChanged()
    }

}
