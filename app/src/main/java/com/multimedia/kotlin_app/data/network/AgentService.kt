package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.modules.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
 * Clase que nos permite acceder a los agentes a través de internet (no en db local)
 * y si lo va a hacer a través de internet, lo hará mediante esta clase, todas las llamadas serán
 * desde esta class
 */
class AgentService @Inject constructor(private val api:ValorantApiClient){

    suspend fun getAgentByID(agentID: String): Agent? {
        return withContext(Dispatchers.IO) {
            val service = api.getAgentId(agentID)
            service.body() ?: api.getAgents().body()?.firstOrNull()
        }
    }

    //método que devuelve lista de agentes
    suspend fun getAllAgents(): List<Agent> {
        return withContext(Dispatchers.IO) {
            val service = api.getAgents()
            service.body() ?: emptyList()
        }
    }
}