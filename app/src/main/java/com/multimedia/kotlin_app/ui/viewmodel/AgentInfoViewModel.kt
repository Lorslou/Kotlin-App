package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.multimedia.kotlin_app.data.AgentRepository
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.Agent
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetSpecificAgentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentInfoViewModel @Inject constructor(
    private val repository: AgentRepository,
    private val getSpecificAgentUseCase: GetSpecificAgentUseCase
) : ViewModel() {

    val favOnOff = MutableLiveData<Boolean>()
    val agentData = MutableLiveData<AgentDataDisplay?>()
    val goBack = MutableLiveData<Unit>()

    fun onCreate(agentID: String) {
        viewModelScope.launch {
            val searchResult = getSpecificAgentUseCase.invoke(agentID)
            agentData.postValue(searchResult)
            val agentFav = repository.getAgentFromFavorites(agentID)
            if (agentFav == null) {
                favOnOff.postValue(false)
            } else {
                favOnOff.postValue(true)
            }
        }

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
                favOnOff.postValue(true)
                repository.addAgentToFavorites(newAgentFav)
            } else {
                // El agente ya está en favoritos, actualizamos el campo isFavorite
                agentFav.isFavorite = !agentFav.isFavorite
                if (!agentFav.isFavorite) { //si isFavorite está en false
                    repository.deleteAgentFromFavorites(agentFav)
                    favOnOff.postValue(false)
                } else { //el agente es favorito, actualiza los datos en la tabla de favs
                    repository.updateAgent(agentFav)
                }
            }
        }
    }

    fun goBackToSearch() {
        goBack.postValue(Unit)
    }

}


