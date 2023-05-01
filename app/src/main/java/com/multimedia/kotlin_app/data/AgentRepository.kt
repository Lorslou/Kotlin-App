package com.multimedia.kotlin_app.data

import com.multimedia.kotlin_app.AgentService
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import javax.inject.Inject

class AgentRepository @Inject constructor(
    private val api: AgentService
) {

    //la 1ª vez que llame al repo, llamará al AgentService, el service hará una llamada al api client
    //recuperará el listado de agentes, se los devuelve al service y éste al repo

    suspend fun getAgentById(agentID: String): AgentDataDisplay {
        val service = api.getAgent(agentID)
        return service
    }
}