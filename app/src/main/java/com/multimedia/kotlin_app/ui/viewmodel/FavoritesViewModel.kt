package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.domain.uc.GetAllFavoriteAgentsUseCase
import com.multimedia.kotlin_app.data.database.entities.AgentEntityFavs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteAgents: GetAllFavoriteAgentsUseCase
) : ViewModel() {

    val favoriteAgents = MutableLiveData<List<AgentEntityFavs>>()


    fun onCreate() {
        viewModelScope.launch {
            val searchResult = getFavoriteAgents.invoke()
            favoriteAgents.postValue(searchResult)
        }
    }
}