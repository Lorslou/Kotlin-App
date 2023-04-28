package com.multimedia.kotlin_app

import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.network.AgentService
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import retrofit2.Response
import javax.inject.Inject

class AgentRepository @Inject constructor(private val api: ValorantApiClient) {

    //la 1ª vez que llame al repo, llamará al AgentService, el service hará una llamada al api client
    //recuperará el listado de agentes, se los devuelve al service y éste al repo

    suspend fun getAllAgents(): Response<List<Agent>> {
        val service = api.getAgents()
        //AgentProvider.agentsList = service
        return service

    }
}