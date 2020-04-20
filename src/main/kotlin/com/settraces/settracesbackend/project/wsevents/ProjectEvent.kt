package com.settraces.settracesbackend.project.wsevents

import com.settraces.settracesbackend.ws.Events
import com.settraces.settracesbackend.ws.UpdateEvent

open class ProjectEvent(val event: Events, val projectId: String, val data: String) : UpdateEvent() {
    override fun stringify(): String {
        return "{\"change\":\"$event\",\"projectId\":\"$projectId\",\"data\":\"$data\"}"
    }
}