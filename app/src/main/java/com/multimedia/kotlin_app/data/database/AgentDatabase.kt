package com.multimedia.kotlin_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.multimedia.kotlin_app.AgentAbilitiesConverter
import com.multimedia.kotlin_app.AgentRoleConverter
import com.multimedia.kotlin_app.data.database.dao.AgentDao
import com.multimedia.kotlin_app.data.database.entities.*

@Database(
    entities = [AgentEntity::class, AgentAbilitiesEntity::class, AgentRoleEntity::class, AgentEntityFavs::class, AllAgentsEntity::class],
    version = 1
)
@TypeConverters(AgentRoleConverter::class, AgentAbilitiesConverter::class)
abstract class AgentDatabase : RoomDatabase() {

    //por cada DAO se crea una abstract fun
    abstract fun getAgentDao(): AgentDao
}

/*
@Database(entities = [AgentEntity::class, AgentAbilitiesEntity::class, AgentRoleEntity::class], version = 1)
@TypeConverters(AgentRoleConverter::class, AgentAbilitiesConverter::class)
abstract class AgentDatabase: RoomDatabase() {

    //por cada DAO se crea una abstract fun
    abstract fun getAgentDao():AgentDao
}
 */