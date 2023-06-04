package com.multimedia.kotlin_app.domain.uc

import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.model.AgentDomain
import javax.inject.Inject

class GetSpecificAgentUseCase @Inject constructor(
    private val repository: AgentRepository
) {

    // suspend operator fun invoke(agentID: String): AgentDataDisplay? = repository.getAgentById(agentID)

    //suspend operator fun invoke(agentID: String): AgentDomain = repository.getAgentFromApi(agentID)



}