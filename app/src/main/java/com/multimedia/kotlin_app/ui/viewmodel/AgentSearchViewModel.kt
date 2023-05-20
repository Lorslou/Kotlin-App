package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.domain.uc.GetSpecificAgentUseCase
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentSearchViewModel @Inject constructor(
    private val getSpecificAgentUseCase: GetSpecificAgentUseCase,
    private val getAllAgentsUseCase: GetAllAgentsUseCase
): ViewModel() {

    val agentDisplay = MutableLiveData<AgentDataDisplay?>()
    val allAgents = MutableLiveData<List<Agent>?>() //mostrar todos los agentes
    val dataLoading = MutableLiveData<Boolean>() //para el círculo de loading
    val favOnOff = MutableLiveData<Boolean>() //control fav


    fun onCreate(agentID: String) {
        viewModelScope.launch {
            dataLoading.postValue(true)
            val searchResult = getSpecificAgentUseCase.invoke(agentID)
            if (searchResult != null) {
                agentDisplay.postValue(searchResult)
            } else {
                agentDisplay.postValue(null) //para que aparezca el toast
            }
            dataLoading.postValue(false)

        }
    }

}