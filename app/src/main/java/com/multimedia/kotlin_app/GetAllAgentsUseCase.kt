package com.multimedia.kotlin_app

import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import javax.inject.Inject

class GetAllAgentsUseCase @Inject constructor(
    private val repository: AgentRepository
) {

    suspend operator fun invoke(): AgentDataDisplay? = repository.getAllAgents()
}