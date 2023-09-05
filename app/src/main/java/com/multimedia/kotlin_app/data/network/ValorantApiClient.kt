package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.data.model.Agent
import retrofit2.Response
import retrofit2.http.GET

/**
 * This class defines the API methods to communicate with the Valorant web service and fetch agent data
 */
interface ValorantApiClient {

    @GET("/v1/agents")
    suspend fun getAgents(): Response<Agent>

}