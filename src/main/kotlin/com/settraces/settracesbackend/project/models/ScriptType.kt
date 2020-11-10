package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonIgnore

class ScriptType(var id: String?, val name: String?, @JsonIgnore val projectId: String?) {
    @JsonIgnore
    var project: Project? = null

    constructor(name: String?, projectId: String?) : this(null, name, projectId) {

    }
}