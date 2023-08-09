package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllAgentsUseCase: GetAllAgentsUseCase
) : ViewModel() {
    val dataLoading = MutableLiveData<Boolean>()
    val agentsDisplay = MutableLiveData<List<AgentDataDisplay>?>()

    fun onCreateFavoritesView() {
        viewModelScope.launch {
            val allAgents = getAllAgentsUseCase.invoke()
            agentsDisplay.postValue(allAgents)
        }
    }
}