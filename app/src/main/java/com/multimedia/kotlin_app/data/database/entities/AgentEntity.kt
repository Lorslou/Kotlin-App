package com.multimedia.kotlin_app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.multimedia.kotlin_app.domain.model.AgentDomain

@Entity(tableName = "agent_data_table")
data class AgentEntity(
    @PrimaryKey @ColumnInfo(name = "uuid") val uuid: String,
    @ColumnInfo(name = "agentName") val agentName: String,
    @ColumnInfo(name = "agentDescription") val agentDescription: String,
    @ColumnInfo(name = "agentRole") val agentRole: AgentRoleEntity,
    @ColumnInfo(name = "agentIcon") val agentIcon: String,
    @ColumnInfo(name = "agentBackground") val agentBackground: String,
    @ColumnInfo(name = "agentInfoPortrait") val agentInfoPortrait: String,
    @ColumnInfo(name = "agentAbilities") val agentAbilities: List<AgentAbilitiesEntity>,
)

@Entity(tableName = "agent_abilities")
data class AgentAbilitiesEntity(
    @PrimaryKey @ColumnInfo(name = "abilitiesSlot") val abilitiesSlot: String,
    @ColumnInfo(name = "abilitiesName") val abilitiesName: String,
    @ColumnInfo(name = "abilitiesIcon") val abilitiesIcon: String
)

@Entity(tableName = "agent_role")
data class AgentRoleEntity(
    @PrimaryKey @ColumnInfo(name = "roleUuid") val roleUuid: String,
    @ColumnInfo(name = "agentRoleIcon") val agentRoleIcon: String,
    @ColumnInfo(name = "agentRoleName") val agentRoleName: String
)

@Entity(tableName = "agent_favorites_table")
data class AgentEntityFavs(
    @PrimaryKey @ColumnInfo(name = "uuid") val uuid: String,
    @ColumnInfo(name = "agentName") val agentName: String,
    @ColumnInfo(name = "agentIcon") val agentIcon: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)

@Entity(tableName = "agent_info_table")
data class AgentToSearch(
    @PrimaryKey @ColumnInfo(name = "uuid") val uuid: String,
    @ColumnInfo(name = "agentName") val agentName: String,
    @ColumnInfo(name = "agentDescription") val agentDescription: String
)

/*
fun AgentDomain.toDatabase() = AgentEntity(
    uuid = uuid,
    agentName = agentName,
    agentDescription = agentDescription,
    agentRole = AgentRoleEntity(
        roleUuid = agentRole.agentRoleUuid,
        agentRoleIcon = agentRole.agentRoleIcon,
        agentRoleName = agentRole.agentRoleName
    ),
    agentIcon = agentIcon,
    agentBackground = agentBackground,
    agentInfoPortrait = agentInfoPortrait,
    agentAbilities = agentAbilities.map {
        AgentAbilitiesEntity(
            abilitiesSlot = it.abilitiesSlot,
            abilitiesName = it.abilitiesName,
            abilitiesIcon = it.abilitiesIcon
        )
    }

)
 */



