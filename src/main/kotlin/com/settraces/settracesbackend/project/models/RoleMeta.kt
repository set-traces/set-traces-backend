package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonIgnore

class RoleMeta(var role: String, var description: String, var actor: String?, var actorId: String?) {

    @JsonIgnore
    var scriptId: String? = null

    init {
        if (this.actor == null && this.actorId ==  null) { // object has no actor -- role is unset
            this.actor = ""
            this.actorId = ""
        }
    }

    constructor(
            role: String,
            description: String,
            actorId: String?,
            scriptId: String,
            actor: String
    ) : this(role, description, actor, actorId) {
        this.scriptId = scriptId
    }
}