package com.multimedia.kotlin_app.ui.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

class AgentAdapter(
    private var agentList: List<AgentDataDisplay> = emptyList(),
    private val accessToAgentInfo: (String) -> Unit
) :
    RecyclerView.Adapter<AgentViewHolder>() {

    //le pasa el/los items(view) al viewHolder y lo crea
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
        )
    }

    //método que le pasa a onCreateViewHolder la información que tiene que pintar/mostrar
    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        holder.render(agentList[position], accessToAgentInfo)
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