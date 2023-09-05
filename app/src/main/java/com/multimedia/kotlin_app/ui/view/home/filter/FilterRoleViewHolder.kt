package com.multimedia.kotlin_app.ui.view.home.filter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ItemClassFilterBinding

class FilterRoleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemClassFilterBinding.bind(view)

    fun render(roleFilter: AgentDataDisplay) {
        Log.i("rol", roleFilter.agentRole.agentRoleName)
        binding.tvRoleFilter.text = roleFilter.agentRole.agentRoleName
    }
}
