package com.multimedia.kotlin_app.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.multimedia.kotlin_app.data.database.entities.AgentRoleEntity

/**
 * This class is used as a type converter to allow Room to store and retrieve lists of
 * AgentRoleEntity objects in the database
 */
class AgentRoleConverter {
    @TypeConverter
    fun fromAgentRoleToString(agentRole: AgentRoleEntity): String {
        return Gson().toJson(agentRole)
    }

    @TypeConverter
    fun fromStringToAgentRole(json: String): AgentRoleEntity {
        return Gson().fromJson(json, AgentRoleEntity::class.java)
    }
}