package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentInfoViewModel @Inject constructor(
    private val repository: AgentRepository
) : ViewModel(){

    val favOnOff = MutableLiveData<Boolean>()
    val favoriteAgents = MutableLiveData<List<AgentEntityFavs>>()

    fun turnOffOnFavorite(agentID: String) {
        viewModelScope.launch {
            val agentFav = repository.getAgentFromDB(agentID)
            agentFav.isFavorite = !agentFav.isFavorite
            repository.updateAgent(agentFav)

            }
        }

    fun loadFavoriteAgents() {
        viewModelScope.launch {
            val agents = repository.getFavoriteAgents()
            favoriteAgents.postValue(agents)
        }
    }

    fun updateAgentFavoriteStatus(agentID: String) {
        viewModelScope.launch {
            val agentFav = repository.getAgentFromDB(agentID)

        }
    }
}


