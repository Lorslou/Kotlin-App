package com.multimedia.kotlin_app.domain.uc

import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.domain.model.AgentDomain
import javax.inject.Inject

class GetAllAgentsUseCase @Inject constructor(
    private val repository: AgentRepository
)
{
/*
suspend operator fun invoke(): List<AgentDomain> {
        val agents = repository.getAgentFromApi()
    }
 */

}