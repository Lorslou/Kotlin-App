package com.multimedia.kotlin_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.multimedia.kotlin_app.data.model.Agent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(

): ViewModel() {

    val agentModel = MutableLiveData<Agent>()
}