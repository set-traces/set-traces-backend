package com.settraces.settracesbackend.project.mappers

import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.models.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Controller
import java.sql.ResultSet

class ProjectMapper(val projectDb: ProjectDb, val getExtras: Boolean = true) : RowMapper<Project> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Project? {
        var project: Project = Project(rs.getString("id"), rs.getString("name"), rs.getString("description"))
        if (getExtras) {
            project.scripts = projectDb.getAllScripts(project)
            project.scriptTypes = projectDb.getScriptTypes(project)
        }
        return project
    }
}