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

class AgentService {

    private val retrofit = NetworkModule.provideRetrofit()

    suspend fun getAgent(agentID: String): AgentDataDisplay {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ValorantApiClient::class.java).getAgentId(agentID) //TODO CAMBIAR LA FORMA DE LLAMAR A LA API

            if (response.isSuccessful && response.body() != null) {
                Log.i("lorena", "funciona :)")
                Log.i("lorena", response.toString())
                val agent1: AgentDataDisplay = response.body()!!.data
                //response.body()!!
                Log.i("lorena", agent1.toString())
                agent1
            } else {
                Log.i("lorena", "NO funciona :)")
                throw AgentNotFoundException()
            }
        }
    }

}

