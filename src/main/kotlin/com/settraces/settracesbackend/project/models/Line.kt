package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonIgnore

class Line {
    var role: String? = null
    var type: ELine? = null
    var text: String? = null
    var id: String? = null
    var roleId: String? = null
    @JsonIgnore
    var scriptId: String? = null

    @JsonIgnore
    var ordering: Int? = null

    constructor(id: String, role: String?, type: String, text: String, scriptId: String, roleId: String?, ordering: Int?) { // used by mapper
        this.id = id
        if (role == null) {
            this.role = ""
        } else {
            this.role = role
        }
        if (roleId == null) {
            this.roleId = ""
        } else {
            this.roleId = roleId
        }
        this.type = ELine.valueOf(type)
        this.text = text
    }

    constructor(type: String?, text: String?, roleId: String?, scriptId: String, ordering: Int?) { // used by create new
        this.type = type?.let { ELine.valueOf(it) }
        this.text = text
        if (roleId == null) {
            this.roleId = ""
        } else {
            this.roleId = roleId
        }
        this.scriptId = scriptId
        this.ordering = ordering
        role = ""
        id = ""
    }

}