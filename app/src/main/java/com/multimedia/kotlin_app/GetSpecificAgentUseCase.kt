package com.multimedia.kotlin_app

import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import javax.inject.Inject

class GetSpecificAgentUseCase @Inject constructor(
    private val repository: AgentRepository
) {

    suspend operator fun invoke(agentID: String): AgentDataDisplay = repository.getAgentById(agentID)




}