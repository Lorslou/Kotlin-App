package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllFavoriteAgentsUseCase
import com.multimedia.kotlin_app.domain.uc.GetSpecificAgentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFavoritesViewModel @Inject constructor(
    private val getSpecificAgentUseCase: GetSpecificAgentUseCase,
    private val getFavoriteAgents: GetAllFavoriteAgentsUseCase
) : ViewModel() {
    val agentDisplay = MutableLiveData<AgentDataDisplay?>()
    val dataLoading = MutableLiveData<Boolean>()
    val favoriteAgents = MutableLiveData<List<AgentEntityFavs>>()

/*
    fun onCreateSearchView(agentID: String) {
        viewModelScope.launch {
            dataLoading.postValue(true)
            val searchResult = getSpecificAgentUseCase.invoke(agentID)
            if (searchResult != null) {
                agentDisplay.postValue(searchResult)
            } else {
                agentDisplay.postValue(null)
            }
            dataLoading.postValue(false)

        }
    }

 */

    fun onCreateFavoritesView() {
        viewModelScope.launch {
            val searchResult = getFavoriteAgents.invoke()
            favoriteAgents.postValue(searchResult)
        }
    }
}