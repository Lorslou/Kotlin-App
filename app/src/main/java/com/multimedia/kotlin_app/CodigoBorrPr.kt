package com.multimedia.kotlin_app

class CodigoBorrPr {

    /*
    private fun searchByAgentId(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val service = retrofit.create(ValorantApiClient::class.java).getAgentId(query)

            if (service.isSuccessful) {
                Log.i("lorena", "funciona :)")

                val response: Agent? = service.body()

                if (response != null) {
                    Log.i("lorena", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.data)
                        binding.progressBar.isVisible = false
                    }
                } else {
                    //TODO DEVOLVER LISTA DE AGENTES ENTERA SI NO ENCUENTRA ID
                }

            } else {
                Log.i("lorena", "NO funciona :)")
            }
        }
    }
     */


    //VIEWMODEL2
    /*
    fun searchByAgentId(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataLoading.value = true
            val service = retrofit.create(ValorantApiClient::class.java).getAgentId(query)

            if (service.isSuccessful) {

                val response: Agent? = service.body()

                if (response != null) {
                    agentModel.postValue(Agent(0, response.data))
                    dataLoading.value = false
                } else {
                    //TODO NO SE ENCONTRÓ AL AGENTE, MANDO TOAST
                    /*
                    val responseAllAgents = AgentRepository.getAllAgents()
                    showAllAgents.postValue(responseAllAgents.data)
                     */
                }
            }
        }

    }
     */

    /*
    class AgentRepositoryImpl(private val agentCache: AgentCache, private val agentRemoteDataSource: AgentRemoteDataSource) : AgentRepository {
    override suspend fun getAgentById(agentID: String): Agent? {
        // Buscamos el agente en la cache
        val agent = agentCache.getAgentById(agentID)
        return if (agent != null) {
            agent
        } else {
            // Si no está en cache, lo buscamos en la API y lo almacenamos en cache
            val agentResponse = agentRemoteDataSource.getAgentById(agentID)
            if (agentResponse.isSuccessful) {
                val agentBody = agentResponse.body()
                if (agentBody != null) {
                    agentCache.saveAgent(agentBody)
                    agentBody
                } else {
                    null
                }
            } else {
                null
            }
        }
    }
     */

    /*
    fun searchAgent(agentID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataLoading.postValue(true)
                val response = apiClient.getAgentId(agentID)

                if (response.isSuccessful) {
                    //val agent = response.body()
                    val agent: Agent? = response.body()

                    if (agent != null) {
                        agentModel.postValue(agent)
                    }
                } else {
                    //TODO
                }
            } catch (excp: Exception) {
                //TODO enviar toast
            }
        }
    }
     */
}