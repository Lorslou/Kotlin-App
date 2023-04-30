package com.multimedia.kotlin_app

import com.google.gson.annotations.SerializedName

//AgentData es el tipo de dato devuelto por la API, pues Agent es una entidad de dominio que representa
// un agente en la lógica de la aplicación
data class AgentData (
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
    @SerializedName("displayIcon") val agentRoleIcon: String,
    @SerializedName("displayName") val agentRoleName: String
)

data class AgentAbilities(
    @SerializedName("slot") val abilitiesSlot: String,
    @SerializedName("displayName") val abilitiesName: String,
    @SerializedName("displayIcon") val abilitiesIcon: String
)
