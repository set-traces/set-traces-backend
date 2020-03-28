package com.settraces.settracesbackend.project.databasehandlers

import com.settraces.settracesbackend.database.ParamHolder
import com.settraces.settracesbackend.project.mappers.*
import com.settraces.settracesbackend.project.models.*
import com.settraces.settracesbackend.project.payload.request.NewScriptRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import java.sql.SQLException
import javax.annotation.processing.Generated


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

    fun create(project: Project): Project? {
        return try {
            val data: HashMap<String, String> = HashMap<String, String>()
            val keyHolder: KeyHolder = GeneratedKeyHolder()
            data.put("name", project.name.toString())
            data.put("description", project.description.toString())
            val result: Int = namedParameterJdbcTemplate!!.update("insert into projects (name, description) values (:name, :description)", ParamHolder().addValue("name", project.name).addValue("description", project.description), keyHolder) // should include created by
            val id: String = keyHolder.keyList.get(0).get("id").toString()
            project.id = id
            project
        } catch (e: Exception) {
            println(e)
            println("got an exception")
            null
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

    fun getRolesMeta(script: Script): List<RoleMeta> {
        return namedParameterJdbcTemplate!!.query("select * from playing_roles pr left join actors a on a.id = pr.actor_id where pr.script_id=uuid(:scriptid)", MapSqlParameterSource().addValue("scriptid", script.id), RoleMetaMapper())
    }

    fun newScript(script: Script): Script {
        //return namedParameterJdbcTemplate!!.update("insert into scripts(description", MapSqlParameterSource().addValue("scriptid"))
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val data: ParamHolder = ParamHolder()
        data.addValue("name", script.name)
        data.addValue("projectId", script.projectId)
        data.addValue("typeId", script.scriptTypeId)
        data.addValue("desc", script.description)
        var result: Int = namedParameterJdbcTemplate!!.update("insert into scripts(name, project_id, script_type_id, description) values (:name, uuid(:projectId), uuid(:typeId), :desc)", data, keyHolder)
//        var result: Int = namedParameterJdbcTemplate!!.update("insert into test(val1, val2) values (:one, :two)", data)
        val scriptId: String = keyHolder.keyList.get(0).get("id").toString()
        return getScriptById(script.projectId!!, scriptId)!!
    }

}