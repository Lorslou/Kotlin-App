package com.multimedia.kotlin_app.data

import com.multimedia.kotlin_app.data.network.AgentService
import com.multimedia.kotlin_app.data.database.dao.AgentDao
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AgentRepository @Inject constructor(
    private val api: AgentService,
    private val agentDao: AgentDao
) {


    /*
    suspend fun getAgentById(agentID: String): AgentDataDisplay? {
    val service = api.getAgent(agentID)
    return service
    }
    */

    suspend fun getAllAgents(): List<AgentDataDisplay>? {
        val service = api.getAllAgents()
        return service
    }

    suspend fun getAgentFromFavorites(agentID: String): AgentEntityFavs? {
        return agentDao.checkIfAgentFavorite(agentID)
    }

    suspend fun updateAgent(agent: AgentEntityFavs) {
        withContext(Dispatchers.IO){
            agentDao.updateAgent(agent)
        }
    }

    suspend fun getFavoriteAgents(): List<AgentEntityFavs>{
        return withContext(Dispatchers.IO){
            agentDao.getFavoriteAgents()
        }
    }

    suspend fun addAgentToFavorites(agent: AgentEntityFavs) {
        agentDao.insertFavoriteAgent(agent)
    }

    suspend fun deleteAgentFromFavorites(agent: AgentEntityFavs) {
        agentDao.deleteFavoriteAgent(agent)
    }

}