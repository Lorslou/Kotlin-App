package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.modules.NetworkModule

/**
Si necesita los datos de internet, se realizará la llamada desde esta clase
 */
class AgentService {
    private val retrofit = NetworkModule.provideRetrofit()
/*
    suspend fun getAgents():List<AgentDataResponse>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(ValorantApiClient::class.java).getAgentName()
            response.body() ?: emptyList()
        }


    }
    +/
 */
}