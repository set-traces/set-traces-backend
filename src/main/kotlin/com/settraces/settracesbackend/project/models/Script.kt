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

    var lines: List<Line>? = arrayListOf(Line("Forteller", "REMARK", "Dette funker dritbra"), Line("Regissor", "REMARK", "Dette er helt utrolig. Hvordan har ingen laget noe slikt før? Og se på den froentenden a!! Det er som å se seg selv i speilet #selvskryt #karantenetrening"))

    var rolesMeta: List<RoleMeta> = arrayListOf(RoleMeta("Forteller", "Sitter bak lukket vindu", "Asgeir"), RoleMeta("Regissor", "Sitter blant publikum og er dyktig imponert", "Jonathan"))

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