package com.settraces.settracesbackend.project.models

import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.jdbc.core.JdbcTemplate


class Project {

    @Id var id: String? = null
    var name: String? = null
    var description: String? = null

    var scriptTypes: List<ScriptType>? = arrayListOf()
    var scripts: List<Script> = arrayListOf()
    constructor(name: String?, description: String?) {
        this.name = name
        this.description = description
    }

    constructor(id: String, name: String, description: String?) {
        this.id = id
        this.name = name
        this.description = description
    }

    private fun _getScriptTypes() {
        println("getting script types")
    }
}