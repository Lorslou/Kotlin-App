package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class is responsible for managing the data and view logic for the agent detail in the
 * application. It handles fetching the agent data, managing the favorite state, and controlling
 * the back navigation in the agent detail view
 */
@HiltViewModel
class AgentInfoViewModel @Inject constructor(
    private val repository: AgentRepository,
    private val getAllAgentsUseCase: GetAllAgentsUseCase
) : ViewModel() {

    val favOnOff = MutableLiveData<Boolean>()
    val agentData = MutableLiveData<AgentDataDisplay?>()
    val goBack = MutableLiveData<Unit>()


    fun onCreate(agentName: String) {
        viewModelScope.launch {
            val searchResult = getAllAgentsUseCase.invoke()
            val filteredAgent = searchResult?.find { agent ->
                agent.uuid.equals(agentName.trim(), ignoreCase = true)
            }
            agentData.postValue(filteredAgent)
            val agentFav = repository.getAgentFromFavorites(agentName)
            if (agentFav == null) {
                favOnOff.postValue(false)
            } else {
                favOnOff.postValue(true)
            }
        }

    }

    fun switchFavoriteAgent(agentID: String) {
        viewModelScope.launch  {
            val agentFav = repository.getAgentFromFavorites(agentID) // checks if the agent is already in the favorites table
            if (agentFav == null) { // if it returns null, the agent is not in favorites, so we insert it
                val agentData = repository.getAllAgents()
                val filteredAgent = agentData?.find { agent ->
                    agent.uuid.equals(agentID.trim(), ignoreCase = true)
                }
                val newAgentFav = AgentEntityFavs(
                    filteredAgent?.uuid.orEmpty(),
                    filteredAgent?.agentName.orEmpty(),
                    filteredAgent?.agentIcon.orEmpty(),
                    true
                )
                favOnOff.postValue(true)
                repository.addAgentToFavorites(newAgentFav)
            } else {
                // the agent is already in favorites, so we update the isFavorite field
                agentFav.isFavorite = !agentFav.isFavorite
                if (!agentFav.isFavorite) { // if isFavorite is false
                    repository.deleteAgentFromFavorites(agentFav)
                    favOnOff.postValue(false)
                } else { // the agent is favorite, update the data in the favorites table
                    repository.updateAgent(agentFav)
                }
            }
        }
    }

    fun goBackToSearch() {
        goBack.postValue(Unit)
    }

}


