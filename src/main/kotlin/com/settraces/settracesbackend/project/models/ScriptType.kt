package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonIgnore

class ScriptType(val id: String?, val name: String?, @JsonIgnore val projectId: String?) {
    @JsonIgnore
    var project: Project? = null
}