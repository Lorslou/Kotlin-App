package com.multimedia.kotlin_app

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.modules.NetworkModule
import com.multimedia.kotlin_app.utilities.AgentNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/*
 * Clase que nos permite acceder a los agentes a través de internet (no en db local)
 * y si lo va a hacer a través de internet, lo hará mediante esta clase, todas las llamadas serán
 * desde esta class
 */
class AgentService @Inject constructor(
    private val apiClient: ValorantApiClient
) {

    suspend fun getAgent(agentID: String): AgentDataDisplay {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getAgentId(agentID)

            if (response.isSuccessful && response.body() != null) {
                Log.i("lorena", "funciona :)")
                Log.i("lorena", response.toString())
                val agentFound: AgentDataDisplay = response.body()!!.data
                Log.i("lorena", agentFound.toString())
                agentFound
            } else {
                Log.i("lorena", "NO funciona :)")
                throw AgentNotFoundException()
            }
        }
    }

    suspend fun getAllAgents(): List<Agent> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getAgents()
            response.body() ?: emptyList()
        }
    }

}

