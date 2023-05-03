package com.multimedia.kotlin_app

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.multimedia.kotlin_app.data.database.entities.AgentRoleEntity

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