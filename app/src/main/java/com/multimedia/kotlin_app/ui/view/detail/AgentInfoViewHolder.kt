package com.multimedia.kotlin_app.ui.view.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.databinding.ItemInfoAgentViewBinding

/**
 * This class is responsible for maintaining and updating the view for each item in the
 * RecyclerView of the AgentInfoAdapter
 */
class AgentInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemInfoAgentViewBinding.bind(view)

    fun render(agentData: AgentDataDisplay) {
        binding.ivBackground.load(agentData.agentBackground)
        binding.ivAgentImage.load(agentData.agentInfoPortrait)
        binding.tvAgentName.text = agentData.agentName
        binding.tvDescription.text = agentData.agentDescription
        binding.ivAgentRoleImage.load(agentData.agentRole.agentRoleIcon)
        binding.tvAgentRoleName.text = agentData.agentRole.agentRoleName
        binding.ivAbility1Image.load(agentData.agentAbilities[0].abilitiesIcon)
        binding.ivAbility2Image.load(agentData.agentAbilities[1].abilitiesIcon)
        binding.ivAbility3Image.load(agentData.agentAbilities[2].abilitiesIcon)
        binding.ivAbility4Image.load(agentData.agentAbilities[3].abilitiesIcon)
        binding.tvAbility1Name.text = agentData.agentAbilities[0].abilitiesName
        binding.tvAbility2Name.text = agentData.agentAbilities[1].abilitiesName
        binding.tvAbility3Name.text = agentData.agentAbilities[2].abilitiesName
        binding.tvAbility4Name.text = agentData.agentAbilities[3].abilitiesName
    }
}
