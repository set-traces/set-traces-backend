package com.settraces.settracesbackend.project.models

class Line {
    var role: String? = null
    var type: ELine? = null
    var text: String? = null

    constructor(role: String, type: String, text: String) {
        this.role = role
        this.type = ELine.valueOf(type)
        this.text = text
    }
}