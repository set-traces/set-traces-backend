package com.settraces.settracesbackend.project.databasehandlers

import com.settraces.settracesbackend.project.mappers.*
import com.settraces.settracesbackend.project.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.sql.SQLException


@Component
class ProjectDb {

    @Bean
    fun getProjectDb(): ProjectDb {
        return ProjectDb()
    }

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    @Autowired
    val namedParameterJdbcTemplate: NamedParameterJdbcTemplate? = null

    fun getAll(): List<Project> {
        return try {
            jdbcTemplate!!.query("select * from projects", ProjectMapper(this))
        } catch (e: SQLException) {
            ArrayList()
        }
    }

    fun getScriptTypes(project: Project): List<ScriptType> { // getScriptTypeForProject
        val scriptTypes: List<ScriptType> = jdbcTemplate!!.query("select * from script_types WHERE project_id=uuid(?)", arrayOf(project.id),  ScriptTypeMapper() )
        for (st: ScriptType in scriptTypes) {
            st.project = project
        }
        return scriptTypes
    }

    fun getProjectById(projectId: String, getExtras: Boolean = true): Project {
        return namedParameterJdbcTemplate!!.queryForObject("select * from projects WHERE id=uuid(:id)", MapSqlParameterSource().addValue("id", projectId), ProjectMapper(this, getExtras))!!
    }

    fun getScriptById(projectId: String, scriptId: String): Script? {
        return namedParameterJdbcTemplate!!.queryForObject("select s.id, s.name, s.description, s.project_id, s.script_type_id, st.name as type from scripts s INNER JOIN script_types st ON s.script_type_id = st.id WHERE s.id=uuid(:id)", MapSqlParameterSource().addValue("id", scriptId), ScriptMapper(this, getProjectById(projectId, false)))
    }

    fun getAllScripts(project: Project): List<Script> {
        return namedParameterJdbcTemplate!!.query("select s.id, s.name, s.description, s.project_id, s.script_type_id, st.name as type from scripts s INNER JOIN script_types st ON s.script_type_id = st.id WHERE s.project_id=uuid(:id)", MapSqlParameterSource().addValue("id", project.id), ScriptMapper(this, project))
    }

    fun getLines(script: Script): List<Line> {
        return namedParameterJdbcTemplate!!.query("select * from lines l inner join playing_roles pr on pr.id = l.prole_id where l.script_id=uuid(:scriptid)", MapSqlParameterSource().addValue("scriptid", script.id), LineMapper())
    }

    fun getRoleMeta(script: Script): List<RoleMeta> {
        return namedParameterJdbcTemplate!!.query("select * from playing_roles pr inner join actors a on a.id = pr.actor_id where pr.script_id=uuid(:scriptid)", MapSqlParameterSource().addValue("scriptid", script.id), RoleMetaMapper())
    }

}