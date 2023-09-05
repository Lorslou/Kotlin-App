package com.multimedia.kotlin_app.ui.view.home.filter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

class FilterRoleAdapter(private var agentRoleList: List<AgentDataDisplay> = emptyList()) :
    RecyclerView.Adapter<FilterRoleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterRoleViewHolder {
        return FilterRoleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_class_filter, parent, false)
        )

    }

    override fun onBindViewHolder(holder: FilterRoleViewHolder, position: Int) {
        holder.render(agentRoleList[position])
        Log.i("rol", agentRoleList.toString())
    }

    override fun getItemCount() = agentRoleList.size

    fun updateAdapter(roleList: List<AgentDataDisplay>) {
        agentRoleList = roleList
        notifyDataSetChanged()
    }

}