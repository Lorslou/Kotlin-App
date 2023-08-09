package com.multimedia.kotlin_app.data.database.dao

import androidx.room.*
import com.multimedia.kotlin_app.data.database.entities.AgentEntity
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs

/**
 * This class defines a series of methods that provide the necessary functionality to access and
 * manipulate data related to agents in the database
 */
@Dao
interface AgentDao {

    // using :agentID indicates that the value will be provided by the method, instead of simply
    // writing agentID, which would be interpreted as a column in the table
    @Query("SELECT * FROM agent_favorites_table WHERE uuid = :agentID")
    suspend fun getAgentRequestedByUser(agentID:String): AgentEntityFavs

    /*
    @Query("SELECT * FROM agent_favorites_table WHERE uuid = :agentID")
    suspend fun checkIfAgentFavorite(agentID: String): AgentEntityFavs
*/
    @Query("SELECT * FROM agent_favorites_table WHERE uuid = :agentNameUser")
    suspend fun checkIfAgentFavorite(agentNameUser: String): AgentEntityFavs

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