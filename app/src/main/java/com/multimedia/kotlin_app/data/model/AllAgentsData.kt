package com.multimedia.kotlin_app.data.model
import com.google.gson.annotations.SerializedName

data class AllAgentsData (
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<AllAgentsDataDisplay>
)

data class AllAgentsDataDisplay(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val agentName: String,
    @SerializedName("description") val agentDescription: String,
    @SerializedName("role") val agentRole: AllAgentsRole,
    @SerializedName("displayIcon") val agentIcon: String,
    @SerializedName("background") val agentBackground: String,
    @SerializedName("bustPortrait") val agentInfoPortrait: String,
    @SerializedName("abilities") val agentAbilities: List<AllAgentsAbilities>
)

data class AllAgentsRole(
    @SerializedName("uuid") val roleUuid: String,
    @SerializedName("displayIcon") val agentRoleIcon: String,
    @SerializedName("displayName") val agentRoleName: String
)

data class AllAgentsAbilities(
    @SerializedName("slot") val abilitiesSlot: String,
    @SerializedName("displayName") val abilitiesName: String,
    @SerializedName("displayIcon") val abilitiesIcon: String
)