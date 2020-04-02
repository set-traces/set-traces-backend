package com.settraces.settracesbackend.project.databasehandlers

import com.settraces.settracesbackend.database.ParamHolder
import com.settraces.settracesbackend.project.mappers.LineMapper
import com.settraces.settracesbackend.project.mappers.RoleMetaMapper
import com.settraces.settracesbackend.project.models.Line
import com.settraces.settracesbackend.project.models.RoleMeta
import com.settraces.settracesbackend.project.models.Script
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.tags.Param

@Component
class ScriptDb {
    @Bean
    fun getScripDb(): ScriptDb {
        return ScriptDb()
    }

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    @Autowired
    val namedParameterJdbcTemplate: NamedParameterJdbcTemplate? = null

    fun newRole(roleMeta: RoleMeta): RoleMeta {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val data: ParamHolder = ParamHolder()

        data.addValue("role", roleMeta.role)
        data.addValue("description", roleMeta.description)
        data.addValue("scriptId", roleMeta.scriptId)
        var sql: String = ""
        if (roleMeta.actorId == null) {
            sql = "insert into playing_roles(role, description, script_id) values (:role, :description, uuid(:scriptId))"
        } else {
            data.addValue("actorId", roleMeta.actorId)
            sql = "insert into playing_roles(role, actor_id, description, script_id) values (:role, uuid(:actorId), :description, uuid(:scriptId))"
        }
        val update = namedParameterJdbcTemplate!!.update(sql, data, keyHolder)
        val roleMetaId: String = keyHolder.keyList.get(0).get("id").toString()
        return getRoleMetaById(roleMetaId)!!
    }

    fun getLineById(id: String): Line? {
        return namedParameterJdbcTemplate!!.queryForObject("select * from lines l left join playing_roles pr on pr.id = l.prole_id where l.id=uuid(:id) order by l.ordering", MapSqlParameterSource().addValue("id", id), LineMapper())
    }

    fun newLine(line: Line): Line? {
        var keyHolder: KeyHolder = GeneratedKeyHolder()
        var ph: ParamHolder = ParamHolder()
        ph.addValue(("type"), line.type.toString())
        ph.addValue("text", line.text)
        ph.addValue("scriptId", line.scriptId)

        var sql: String = ""
        if (line.roleId != null && !line.roleId.equals("")) {
            sql = "insert into lines (type, text, prole_id, script_id) values (:type, :text, (select id from playing_roles where id=uuid(:roleId) AND script_id=uuid(:scriptId)), uuid(:scriptId))"
            ph.addValue("roleId", line.roleId)
        } else {
            sql = "insert into lines (type, text, script_id) values (:type, :text, uuid(:scriptId))"
        }
        namedParameterJdbcTemplate!!.update(sql, ph, keyHolder)
        var id: String = keyHolder.keyList.get(0).get("id").toString()

        val updateSelfData: ParamHolder = ParamHolder()
        updateSelfData.addValue("scriptId", line.scriptId)
        updateSelfData.addValue("id", id)
        var updateSelfSql: String = ""
        if (line.ordering == null) {
            println("insert at the end")
            updateSelfSql = "update lines set ordering=(select ordering+1 from lines where script_id=uuid(:scriptId) order by ordering desc limit 1) where id=uuid(:id)"
        } else {
            println("insert in middle")
            val tmpData: ParamHolder = ParamHolder()
            tmpData.addValue("scriptId", line.scriptId)
            tmpData.addValue("ordering", line.ordering)
            var updateAllSql: String = "update lines set ordering = ordering + 1 where script_id=uuid(:scriptId) and ordering >= :ordering"
            namedParameterJdbcTemplate!!.update(updateAllSql, tmpData)
            updateSelfData.addValue("ordering", line.ordering)
            updateSelfSql = "update lines set ordering=:ordering where id=uuid(:id)"
        }

        namedParameterJdbcTemplate!!.update(updateSelfSql, updateSelfData)

        return getLineById(id)
    }

    fun getRoleMetaById(id: String): RoleMeta? {
        return namedParameterJdbcTemplate!!.queryForObject("select * from playing_roles pr left join actors a on a.id = pr.actor_id where pr.id=uuid(:id)", MapSqlParameterSource().addValue("id", id), RoleMetaMapper())
    }

    fun updateNameForScript(projectId: String, scriptId: String, name: String): Boolean {
        var res = namedParameterJdbcTemplate!!.update("update scripts set name=:name WHERE id=uuid(:scriptId)", MapSqlParameterSource().addValue("name", name).addValue("scriptId", scriptId))
        return res == 1
    }

    fun updateDescriptionForScript(projectId: String, scriptId: String, description: String): Boolean {
        var res = namedParameterJdbcTemplate!!.update("update scripts set description=:desc WHERE id=uuid(:scriptId)", MapSqlParameterSource().addValue("desc", description).addValue("scriptId", scriptId))
        return res == 1
    }
}