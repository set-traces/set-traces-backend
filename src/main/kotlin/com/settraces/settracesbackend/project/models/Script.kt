package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id

class Script {

    @Id
    var id: String? = null
    var name: String? = null
    var description: String? = null
    var type: String? = null

    @JsonIgnore
    var projectId: String? = null

    @JsonIgnore
    var scriptTypeId: String? = null

    @JsonIgnore
    var project: Project? = null

    @JsonIgnore
    var scriptType: ScriptType? = null

    @JsonIgnore
    var lines: List<Line>? = null

    constructor(id: String, name: String, description: String, type: String, projectId: String, scriptTypeId: String) {
        this.id = id
        this.name = name
        this.description = description
        this.type = type
        this.projectId = projectId
        this.scriptTypeId = scriptTypeId
    }


    constructor(name: String, description: String, scriptType: ScriptType, project: Project?) {
        this.name = name
        this.description = description
        this.scriptType = scriptType
        this.type = scriptType.name
        this.project = project
        this.projectId = project!!.id
    }


}