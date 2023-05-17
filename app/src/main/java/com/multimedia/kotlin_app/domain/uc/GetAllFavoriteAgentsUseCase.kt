package com.multimedia.kotlin_app.domain.uc

import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import javax.inject.Inject

class GetAllFavoriteAgentsUseCase @Inject constructor(
    private val repository: AgentRepository
) {
    suspend operator fun invoke(): List<AgentEntityFavs> = repository.getFavoriteAgents()

}