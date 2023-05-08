package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AgentService @Inject constructor(
    private val apiClient: ValorantApiClient
) {

    suspend fun getAgent(agentID: String): AgentDataDisplay? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getAgentId(agentID)

            if (response.isSuccessful) {
                val agentFound: AgentDataDisplay? = response.body()?.data
                agentFound
            } else {
                null
            }
        }
    }

    suspend fun getAllAgents(): Agent? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getAgents()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }

        }
    }



}

