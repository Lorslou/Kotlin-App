package com.multimedia.kotlin_app.data.model

import com.google.gson.annotations.SerializedName

data class Agent(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: AgentDataDisplay
)

data class AgentDataDisplay(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val agentName: String,
    @SerializedName("description") val agentDescription: String,
    @SerializedName("role") val agentRole: AgentRole,
    @SerializedName("displayIcon") val agentIcon: String,
    @SerializedName("background") val agentBackground: String,
    @SerializedName("bustPortrait") val agentInfoPortrait: String,
    @SerializedName("abilities") val agentAbilities: List<AgentAbilities>
)

data class AgentRole(
    @SerializedName("uuid") val roleUuid: String,
    @SerializedName("displayIcon") val agentRoleIcon: String,
    @SerializedName("displayName") val agentRoleName: String
)

data class AgentAbilities(
    @SerializedName("slot") val abilitiesSlot: String,
    @SerializedName("displayName") val abilitiesName: String,
    @SerializedName("displayIcon") val abilitiesIcon: String
)
