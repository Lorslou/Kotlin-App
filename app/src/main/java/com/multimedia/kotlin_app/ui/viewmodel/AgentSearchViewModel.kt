package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class is responsible for managing the data and view logic for the agent search in the application
 */
@HiltViewModel
class AgentSearchViewModel @Inject constructor(
    private val getAllAgentsUseCase: GetAllAgentsUseCase,
    private val repository: AgentRepository
) : ViewModel() {

    val filteredAgentList = MutableLiveData<List<AgentDataDisplay>?>()
    val dataLoading = MutableLiveData<Boolean>()
    val favOnOff = MutableLiveData<Boolean>()
    val showDialog = MutableLiveData<Unit>()

    fun onCreate(agentName: String) {
        viewModelScope.launch {
            dataLoading.postValue(true)
            val agents = getAllAgentsUseCase.invoke()
            val agentsFav = repository.getFavoriteAgents()
            if (agents != null) {
                val filteredAgent = agents.filter { agent ->
                    agent.agentName.contains(
                        agentName,
                        ignoreCase = true
                    )
                }
                filteredAgentList.postValue(filteredAgent)
            } else {
                filteredAgentList.postValue(null) //para que aparezca el toast
            }
            dataLoading.postValue(false)
        }
    }

}