package com.settraces.settracesbackend.project.payload.resposne

import com.settraces.settracesbackend.project.models.Line
import com.settraces.settracesbackend.project.models.Script

class ScriptResponse(var lines: List<Line>?, var name: String, var description: String, var projectId: String) {
    constructor(script: Script) : this(script.lines, script.name!!, script.description!!, script.projectId!!)
}