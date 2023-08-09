package com.multimedia.kotlin_app.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.multimedia.kotlin_app.data.model.AgentDataDisplay
import com.multimedia.kotlin_app.domain.uc.GetAllAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class allows you to manage the data and logic related to the home screen of the application
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllAgentsUseCase: GetAllAgentsUseCase
) : ViewModel() {
    val dataLoading = MutableLiveData<Boolean>()
    val agentsDisplay = MutableLiveData<List<AgentDataDisplay>?>()

    fun onCreateHomeView() {
        viewModelScope.launch {
            val allAgents = getAllAgentsUseCase.invoke()
            agentsDisplay.postValue(allAgents)
        }
    }
}