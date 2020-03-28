package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonIgnore

class RoleMeta(var role: String, var description: String, var actor: String?, var actorId: String?, var id: String?) { // used by mapper

    @JsonIgnore
    var scriptId: String? = null

    init {
        if (this.actor == null && this.actorId ==  null) { // object has no actor -- role is unset
            this.actor = ""
            this.actorId = ""
        }
    }

    constructor( // used by create new role
            role: String,
            description: String,
            actorId: String?,
            scriptId: String,
            actor: String,
            roleMapperId: String?
    ) : this(role, description, actor, actorId, roleMapperId) {
        println("this is scriptId")
        println(scriptId)
        this.scriptId = scriptId
    }
}