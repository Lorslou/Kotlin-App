package com.multimedia.kotlin_app.data.database.dao

import androidx.room.*
import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.AgentDataDisplay

@Dao
interface AgentDao {

    //se usa :agentID para indicar que ese valor será proporcionado por el método, en lugar de poner
    // simplemente agentID que se interpretaría como una columna de la tabla
    @Query("SELECT * FROM agent_favorites_table WHERE uuid = :agentID")
    suspend fun getAgentRequestedByUser(agentID:String): AgentEntityFavs

    @Query("SELECT * FROM agent_favorites_table WHERE uuid = :agentID")
    suspend fun checkIfAgentFavorite(agentID: String): AgentEntityFavs

    @Query("SELECT * FROM agent_data_table ORDER BY agentName DESC")
    suspend fun getAllAgents(): List<AgentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAgents(quotes:List<AgentEntity>)

    @Update
    suspend fun updateAgent(agent: AgentEntityFavs)

    @Query("DELETE FROM agent_data_table")
    suspend fun deleteAllAgents()

    //en SQLite se usa = 1 para referirnos a true
    @Query("SELECT * FROM agent_favorites_table WHERE isFavorite = 1")
    suspend fun getFavoriteAgents(): List<AgentEntityFavs>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAgent(agent: AgentEntityFavs)

    @Delete
    suspend fun deleteFavoriteAgent(agent: AgentEntityFavs)

}