package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.data.model.Agent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApiClient {

    @GET("/v1/agents/{agentUuid}")
    suspend fun getAgentId(@Path("agentUuid") idAgent: String): Response<Agent>

    //TODO QUITAR EL V1
    @GET("/v1/agents")
    suspend fun getAgents(): Response<Agent>

}