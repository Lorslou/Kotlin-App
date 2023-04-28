package com.multimedia.kotlin_app

import com.multimedia.kotlin_app.data.model.Agent

class AgentProvider {
    companion object {
        var agentsList: List<Agent> = emptyList()
    }
}