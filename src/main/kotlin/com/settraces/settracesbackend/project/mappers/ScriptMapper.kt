package com.settraces.settracesbackend.project.mappers

import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.models.Script
import com.settraces.settracesbackend.project.models.ScriptType
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ScriptMapper(val projectDb: ProjectDb, val project: Project) : RowMapper<Script> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Script? {

        var script: Script =  Script(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getString("type"), rs.getString("project_id"), rs.getString("script_type_id"))
        script.project = project
        script.scriptType = ScriptType(rs.getString("script_type_id"), rs.getString("type"), rs.getString("project_id"))
        return script
    }
}