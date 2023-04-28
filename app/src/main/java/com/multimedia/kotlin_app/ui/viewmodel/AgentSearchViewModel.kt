package com.multimedia.kotlin_app.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.network.ValorantApiClient
import com.multimedia.kotlin_app.modules.NetworkModule
import com.multimedia.kotlin_app.ui.view.AgentInfoViewActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class AgentSearchViewModel @Inject constructor(

): ViewModel() {

    private var retrofit: Retrofit = NetworkModule.provideRetrofit()

    val agentModel = MutableLiveData<Agent>() //mostrar un solo agente
    val showAllAgents = MutableLiveData<List<Agent>>() //mostrar todos los agentes
    val dataLoading = MutableLiveData<Boolean>() //para el círculo de loading


    //aquí irían las diferentes peticiones a la BD


    //su llamada notifica al viewmodel que la pantalla se ha creado y podemos empezar a realizar cosas
    fun onCreate() {
        viewModelScope.launch {

        }
    }


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


    fun accessToAgentInfo(agentID: String, contextSearchView: Context) {
        val intent = Intent(contextSearchView, AgentInfoViewActivity::class.java)
        intent.putExtra(AgentInfoViewActivity.AGENT_UUID, agentID)
        contextSearchView.startActivity(intent)
    }
}