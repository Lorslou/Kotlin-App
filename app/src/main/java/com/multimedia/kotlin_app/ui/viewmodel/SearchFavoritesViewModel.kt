package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllAgentsUseCase
import com.multimedia.kotlin_app.domain.uc.GetAllFavoriteAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class will manage the data and logic related to the favorite agents search screen
 */
@HiltViewModel
class SearchFavoritesViewModel @Inject constructor(
    private val getFavoriteAgents: GetAllFavoriteAgentsUseCase,
    private val getAllAgentsUseCase: GetAllAgentsUseCase,
    private val repository: AgentRepository,
) : ViewModel() {

    val agentDisplay = MutableLiveData<AgentDataDisplay?>()
    val dataLoading = MutableLiveData<Boolean>()
    val favoriteAgents = MutableLiveData<List<AgentEntityFavs>>()
    val filteredAgentList = MutableLiveData<List<AgentDataDisplay>?>() //filtrar por nombre
    val favOnOff = MutableLiveData<Boolean>()
    val showDialog = MutableLiveData<Unit>()

    fun onCreateFavoritesView() {
        viewModelScope.launch {
            val searchResult = getFavoriteAgents.invoke()
            favoriteAgents.postValue(searchResult)
        }
    }

    fun onCreateSearch(agentName: String) {
        viewModelScope.launch {
            dataLoading.postValue(true)
            val agents = getAllAgentsUseCase.invoke()
            //val agentsFav = repository.getFavoriteAgents()//TODO
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