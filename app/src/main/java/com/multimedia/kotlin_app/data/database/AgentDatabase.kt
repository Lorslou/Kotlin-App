package com.multimedia.kotlin_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.multimedia.kotlin_app.data.database.dao.AgentDao
import com.multimedia.kotlin_app.data.database.entities.AgentAbilitiesEntity
import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.data.database.entities.AgentRoleEntity

@Database(entities = [AgentEntity::class, AgentAbilitiesEntity::class, AgentRoleEntity::class], version = 1)
abstract class AgentDatabase: RoomDatabase() {

    //por cada DAO se crea una abstract fun
    abstract fun getAgentDao():AgentDao
}