package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This class encapsulates the communication logic to fetch agent data. It also makes use of
 * dependency injection to obtain an instance of the API client
 */
class AgentService @Inject constructor(
    private val apiClient: ValorantApiClient
) {

    suspend fun getAllAgents(): List<AgentDataDisplay>? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getAgents()

            if (response.isSuccessful) {
                val agentFound: List<AgentDataDisplay>? = response.body()?.data
                agentFound
            } else {
                null
            }

        }
    }

}

