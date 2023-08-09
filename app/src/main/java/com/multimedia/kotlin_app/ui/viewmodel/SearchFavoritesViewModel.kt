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

/**
 * This class will manage the data and logic related to the favorite agents search screen
 */
@HiltViewModel
class SearchFavoritesViewModel @Inject constructor(
    private val getFavoriteAgents: GetAllFavoriteAgentsUseCase
) : ViewModel() {
    val agentDisplay = MutableLiveData<AgentDataDisplay?>()
    val dataLoading = MutableLiveData<Boolean>()
    val favoriteAgents = MutableLiveData<List<AgentEntityFavs>>()

    fun onCreateFavoritesView() {
        viewModelScope.launch {
            val searchResult = getFavoriteAgents.invoke()
            favoriteAgents.postValue(searchResult)
        }
    }
}