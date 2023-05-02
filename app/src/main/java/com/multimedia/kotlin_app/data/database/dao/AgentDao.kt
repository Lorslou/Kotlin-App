package com.multimedia.kotlin_app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

@Dao
interface AgentDao {

    @Query("SELECT uuid FROM agent_data_table")
    suspend fun getAgentRequestedByUser(): AgentEntity

    @Query("SELECT * FROM agent_data_table ORDER BY agentName DESC")
    suspend fun getAllAgents(): List<AgentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAgents(quotes:List<AgentEntity>)

    @Query("DELETE FROM agent_data_table")
    suspend fun deleteAllAgents()
}