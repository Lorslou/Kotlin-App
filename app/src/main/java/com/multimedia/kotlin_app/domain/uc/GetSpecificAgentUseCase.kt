package com.multimedia.kotlin_app.domain.uc

import com.multimedia.kotlin_app.data.AgentRepository
import javax.inject.Inject

/**
 * This class implements a use case that is used to fetch one agent. It promotes
 * separation of responsibilities in the app
 */
class GetSpecificAgentUseCase @Inject constructor(
    private val repository: AgentRepository
) {

    // suspend operator fun invoke(agentID: String): AgentDataDisplay? = repository.getAgentById(agentID)

    //suspend operator fun invoke(agentID: String): AgentDomain = repository.getAgentFromApi(agentID)

}