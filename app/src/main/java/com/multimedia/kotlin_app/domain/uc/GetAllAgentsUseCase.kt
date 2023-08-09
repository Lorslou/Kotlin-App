package com.multimedia.kotlin_app.domain.uc

import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import javax.inject.Inject

/**
 * This class implements a use case that is used to fetch all agents. It promotes
 * separation of responsibilities in the app
 */
class GetAllAgentsUseCase @Inject constructor(
    private val repository: AgentRepository
) {

    suspend operator fun invoke(): List<AgentDataDisplay>? = repository.getAllAgents()
}