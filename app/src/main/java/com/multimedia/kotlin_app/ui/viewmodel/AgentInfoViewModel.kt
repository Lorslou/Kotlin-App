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
) : ViewModel() {

    val favOnOff = MutableLiveData<Boolean>()
    val favoriteAgents = MutableLiveData<List<AgentEntityFavs>>()

    fun onCreate(agentID: String) {
        switchFavoriteAgent(agentID)
    }

    fun switchFavoriteAgent(agentID: String) {
        viewModelScope.launch  {
            val agentFav = repository.getAgentFromFavorites(agentID) //comprueba si el agente ya está en la tabla de favoritos
            if (agentFav == null) { // Si devuelve null, el agente no está en favoritos, lo insertamos
                val agentData = repository.getAgentById(agentID)
                val newAgentFav = AgentEntityFavs(
                    agentData?.uuid.orEmpty(),
                    agentData?.agentName.orEmpty(),
                    agentData?.agentIcon.orEmpty(),
                    true
                )
                repository.addAgentToFavorites(newAgentFav)
            } else {
                // El agente ya está en favoritos, actualizamos el campo isFavorite
                agentFav.isFavorite = !agentFav.isFavorite
                if (!agentFav.isFavorite) { //si isFavorite está en false
                    repository.deleteAgentFromFavorites(agentFav)
                } else { //el agente es favorito, actualiza los datos en la tabla de favs
                    repository.updateAgent(agentFav)
                }
            }
        }
    }

}


