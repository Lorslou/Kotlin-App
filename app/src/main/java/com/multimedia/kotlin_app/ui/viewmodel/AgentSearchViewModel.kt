package com.multimedia.kotlin_app.ui.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.util.Log
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
) : ViewModel() {

    val allAgents = MutableLiveData<List<AgentDataDisplay>?>()
    val filteredAgentList = MutableLiveData<List<AgentDataDisplay>?>()
    val dataLoading = MutableLiveData<Boolean>()
    val favOnOff = MutableLiveData<Boolean>()
    val showDialog = MutableLiveData<Unit>()

    fun onCreate(agentName: String) {
        viewModelScope.launch {
            dataLoading.postValue(true)
            val agents = getAllAgentsUseCase.invoke()
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


/*
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
*/
}