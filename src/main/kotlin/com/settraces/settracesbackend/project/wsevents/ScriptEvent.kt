package com.settraces.settracesbackend.project.wsevents

import com.settraces.settracesbackend.ws.Events
import com.settraces.settracesbackend.ws.UpdateEvent

class ScriptEvent(event: Events, projectId: String, data: String, val scriptId: String) : ProjectEvent(event, projectId, data) {
    override fun stringify(): String {
        return "{\"change\":\"$event\",\"projectId\":\"$projectId\",\"data\":\"$data\",\"scriptId\":\"$scriptId\"}"
    }
}