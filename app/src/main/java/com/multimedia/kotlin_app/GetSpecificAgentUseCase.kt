package com.multimedia.kotlin_app

import com.multimedia.kotlin_app.data.model.Agent

class GetSpecificAgentUseCase {

    private val repository = AgentRepository()

    suspend operator fun invoke(agentID: String): Agent? = repository.getAgentById(agentID)




}