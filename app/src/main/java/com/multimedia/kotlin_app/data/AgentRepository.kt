package com.multimedia.kotlin_app.data

import com.multimedia.kotlin_app.domain.model.AgentDomain
import com.multimedia.kotlin_app.AgentService
import com.multimedia.kotlin_app.data.database.dao.AgentDao
import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.domain.model.toDomain
import javax.inject.Inject

class AgentRepository @Inject constructor(
    private val api: AgentService,
    private val agentDao: AgentDao
) {

    //la 1ª vez que llame al repo, llamará al AgentService, el service hará una llamada al api client
    //recuperará el listado de agentes, se los devuelve al service y éste al repo

    /*
        suspend fun getAgentById(agentID: String): AgentDataDisplay {
        val service = api.getAgent(agentID)
        return service
    }
     */

    suspend fun getAgentFromApi(agentID: String): AgentDomain {
        val service = api.getAgent(agentID)
        return service.toDomain()
    }

    suspend fun getAllAgentsFromApi(): List<AgentDomain> {
        val service = api.getAllAgents()
        return service.map { it.toDomain() }
    }



    suspend fun getAllAgentsFromDatabase(): List<AgentDomain> {
        val response: List<AgentEntity> = agentDao.getAllAgents()
        return response.map { it.toDomain() }
    }



}