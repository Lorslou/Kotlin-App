package com.multimedia.kotlin_app

import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

class GetSpecificAgentUseCase {

    private val repository = AgentRepository()

    suspend operator fun invoke(agentID: String): AgentDataDisplay = repository.getAgentById(agentID)




}