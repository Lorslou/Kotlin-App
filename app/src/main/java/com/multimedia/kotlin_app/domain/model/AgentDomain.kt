package com.multimedia.kotlin_app.domain.model

import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.data.model.AgentAbilities

/**
 * This class allows converting the agent objects retrieved from the API into domain
 * objects for use in the application logic
 */
data class AgentDomain(
    val uuid: String,
    val agentName: String,
    val agentDescription: String,
    val agentRole: AgentRole,
    val agentIcon: String,
    val agentBackground: String,
    val agentInfoPortrait: String,
    val agentAbilities: List<AgentAbilities>
)


data class AgentRole(
    val agentRoleUuid: String,
    val agentRoleIcon: String,
    val agentRoleName: String
)


data class AgentAbilities(
    val abilitiesSlot: String,
    val abilitiesName: String,
    val abilitiesIcon: String
)


fun AgentDataDisplay.toDomain() = AgentDomain(
    uuid,
    agentName,
    agentDescription,
    agentRole = AgentRole(
        agentRole.roleUuid,
        agentRole.agentRoleIcon,
        agentRole.agentRoleName
    ),
    agentIcon,
    agentBackground,
    agentInfoPortrait,
    agentAbilities = agentAbilities.map {
        AgentAbilities(
            abilitiesSlot = it.abilitiesSlot,
            abilitiesName = it.abilitiesName,
            abilitiesIcon = it.abilitiesIcon
        )
    }
)


fun AgentEntity.toDomain() = AgentDomain(
    uuid,
    agentName,
    agentDescription,
    agentRole = AgentRole(
        agentRole.roleUuid,
        agentRole.agentRoleIcon,
        agentRole.agentRoleName
    ),
    agentIcon,
    agentBackground,
    agentInfoPortrait,
    agentAbilities = agentAbilities.map {
        AgentAbilities(
            abilitiesSlot = it.abilitiesSlot,
            abilitiesName = it.abilitiesName,
            abilitiesIcon = it.abilitiesIcon
        )
    }
)
