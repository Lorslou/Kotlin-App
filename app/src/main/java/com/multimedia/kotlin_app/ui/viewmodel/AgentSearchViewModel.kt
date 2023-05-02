package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.domain.uc.GetSpecificAgentUseCase
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentSearchViewModel @Inject constructor(
    private val getSpecificAgentUseCase: GetSpecificAgentUseCase
): ViewModel() {

    val agentModel = MutableLiveData<Agent?>() //mostrar un solo agente
    val agentDisplay = MutableLiveData<AgentDataDisplay?>()
    val showAllAgents = MutableLiveData<List<Agent>>() //mostrar todos los agentes
    val dataLoading = MutableLiveData<Boolean>() //para el círculo de loading


    //su llamada notifica al viewmodel que la pantalla se ha creado y podemos empezar a realizar cosas
    fun onCreate(agentID: String) {
        viewModelScope.launch {
            dataLoading.postValue(true)
            //val searchResult = GetSpecificAgentUseCase().invoke(agentID)
            val searchResult = getSpecificAgentUseCase.invoke(agentID)

            if (searchResult)
            agentDisplay.postValue(searchResult)
            dataLoading.postValue(false)

        }
    }

}