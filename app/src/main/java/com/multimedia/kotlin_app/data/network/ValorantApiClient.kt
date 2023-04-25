package com.multimedia.kotlin_app.data.network

import com.multimedia.kotlin_app.data.model.Agent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApiClient {

    @GET("/v1/agents/{agentUuid}")
    suspend fun getAgentId(@Path("agentUuid") idAgent: String): Response<Agent>
    //suspend fun getAgentId(@Path("agentUuid") idAgent: String): Response<List<Agent>>

    @GET("/v1/agents")
    suspend fun getAgents(): Response<List<Agent>>

    @GET("/v1/weapons")
    suspend fun getWeapons(): Response<List<Agent>>

}