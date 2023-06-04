package com.multimedia.kotlin_app.ui.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.ui.view.home.HomeViewHolder


class AgentInfoAdapter(
    private var agentInfo: AgentDataDisplay? = null
) : RecyclerView.Adapter<AgentInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentInfoViewHolder {
        return AgentInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_info_agent_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AgentInfoViewHolder, position: Int) {
        agentInfo?.let { holder.render(it) }
    }

    override fun getItemCount() = if (agentInfo != null) 1 else 0

    fun updateAdapter(agentSearch: AgentDataDisplay) {
        this.agentInfo = agentSearch
        notifyDataSetChanged()
    }


}
