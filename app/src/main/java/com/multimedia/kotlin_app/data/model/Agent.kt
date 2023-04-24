package com.multimedia.kotlin_app.data.model

import com.google.gson.annotations.SerializedName

data class Agent (
    @SerializedName("status") val status: Int,
    //@SerializedName("data") val agentData: List<AgentInfoResponse>
)

/*
data class AgentInfoResponse(
    @SerializedName("uuid") val agentID: String,
    @SerializedName("displayName") val agentName: String,
    @SerializedName("description") val agentDescription: String,
    //@SerializedName("role") val agentRole: AgentRoleResponse
)
*/

/*
data class AgentRoleResponse(
    @SerializedName("displayName") val agentRoleName: String
)

 */