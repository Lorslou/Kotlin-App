package com.multimedia.kotlin_app.ui.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.R
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

/**
 * This is an adapter used to display agent information in the RecyclerView
 * of the search fragment
 */
class AgentAdapter(
    private var agentList: List<AgentDataDisplay> = emptyList(),
    private val accessToAgentInfo: (String) -> Unit
) :
    RecyclerView.Adapter<AgentViewHolder>() {

    // it passes the item(s) (view) to the viewHolder and creates it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
        )
    }

    // func that passes the information to be displayed/painted to onCreateViewHolder
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