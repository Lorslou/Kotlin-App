package com.multimedia.kotlin_app.data

import com.multimedia.kotlin_app.domain.model.AgentDomain
import com.multimedia.kotlin_app.AgentService
import com.multimedia.kotlin_app.data.database.dao.AgentDao
import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AgentRepository @Inject constructor(
    private val api: AgentService,
    private val agentDao: AgentDao
) {


    suspend fun getAgentById(agentID: String): AgentDataDisplay? {
    val service = api.getAgent(agentID)
    return service
    }

    suspend fun getAllAgents(): Agent? {
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






    //-------------------------------------------------------
    /*
    suspend fun getAgentFromApi(agentID: String): AgentDomain {
        val service = api.getAgent(agentID)
        return service.toDomain()
    }
     */

    /*
    suspend fun getAllAgentsFromApi(): List<AgentDomain> {
        val service: List<Agent> = api.getAllAgents()
        return service.map { it.toDomain() }
    }
     */


    /*
    suspend fun getAllAgentsFromDatabase(): List<AgentDomain> {
        val response: List<AgentEntity> = agentDao.getAllAgents()
        return response.map { it.toDomain() }
    }
     */

    /*
    suspend fun getAgentFromDB(agentID: String): AgentEntityFavs {
        return agentDao.getAgentRequestedByUser(agentID)
    }
     */






}